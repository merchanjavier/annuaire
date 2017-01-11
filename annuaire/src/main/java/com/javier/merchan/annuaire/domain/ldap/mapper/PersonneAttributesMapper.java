package com.javier.merchan.annuaire.domain.ldap.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.stereotype.Component;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;

/**
 * AttributesMapper utilisé par ldapTemplate pour la classe Personne. Passe-plat pour un converter Dozer
 * 
 * @author Javier MERCHAN
 */
@Component
public class PersonneAttributesMapper implements AttributesMapper<Personne> {

	@Autowired
	private PersonneConverter converter;

	@Override
	public Personne mapFromAttributes(Attributes attrs) throws NamingException {
		return converter.convertFrom(attrs);
	}

	/**
	 * Mappe un objet Attributes à partir d'un Objet Personne
	 * 
	 * @param personne
	 * @return
	 */
	public Attributes mapFromPersonne(Personne personne) {
		return converter.convertTo(personne);
	}
}
