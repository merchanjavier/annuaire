package com.javier.merchan.annuaire.domain.ldap.repository;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;
import com.javier.merchan.annuaire.domain.ldap.mapper.PersonneAttributesMapper;

/**
 * Repository LDAP pour Personne
 * 
 * @author Javier MERCHAN
 */
@Component
public class PersonneRepoImpl implements PersonneRepo {

	@Autowired
	private LdapTemplate ldapTemplate;

	@Autowired
	private PersonneAttributesMapper personneAttributesMapper;

	@Override
	public List<Personne> trouverTous() {
		return ldapTemplate.search(query().where("objectclass").is("person"), personneAttributesMapper);
	}

	@Override
	public Personne trouverParUid(String uid) {
		List<Personne> resultats = ldapTemplate.search(query().where("objectclass").is("person").and(PersonneRepo.IDENTIFIANT).is(uid), personneAttributesMapper);

		return premierResultat(resultats);
	}

	private Personne premierResultat(List<Personne> resultats) {
		return resultats != null && !resultats.isEmpty() ? resultats.iterator().next() : null;
	}

	@Override
	public void mettreAJour(Personne personne) {
		ldapTemplate.modifyAttributes(construireDn(personne), extraireLesModificationItems(personne));
	}

	private ModificationItem[] extraireLesModificationItems(Personne personne) {
		List<ModificationItem> modificationItems = new ArrayList<>();

		Enumeration<? extends Attribute> attributesElements = personneAttributesMapper.mapFromPersonne(personne).getAll();
		while (attributesElements.hasMoreElements()) {
			modificationItems.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attributesElements.nextElement()));
		}

		return modificationItems.toArray(new ModificationItem[modificationItems.size()]);
	}

	private Name construireDn(Personne p) {
		return LdapNameBuilder.newInstance().add(PersonneRepo.UNITE_OPERATIONNELLE, p.getUniteOperationnelle()).add(PersonneRepo.NOM, p.getNom()).build();
	}
}