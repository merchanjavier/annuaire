package com.javier.merchan.annuaire.domain.ldap.mapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;
import com.javier.merchan.annuaire.domain.ldap.repository.PersonneRepo;

/**
 * Converter Dozer Personne <-> Attributes LDAP
 * 
 * @author Javier MERCHAN
 */
@Component
public class PersonneConverter extends DozerConverter<Personne, Attributes> {

	public final Logger logger = LoggerFactory.getLogger(getClass());

	public PersonneConverter() {
		super(Personne.class, Attributes.class);
	}

	@Override
	public Personne convertFrom(Attributes attributes, Personne personne) {
		Personne result = new Personne();

		Attribute uid = attributes.get(PersonneRepo.IDENTIFIANT);
		Attribute cn = attributes.get(PersonneRepo.NOM);
		Attribute sn = attributes.get(PersonneRepo.NOM_DE_FAMILLE);
		Attribute givenname = attributes.get(PersonneRepo.PRENOM);
		Attribute mail = attributes.get(PersonneRepo.MAIL);
		Attribute telephoneNumber = attributes.get(PersonneRepo.TELEPHONE);
		Attribute st = attributes.get(PersonneRepo.SITE);
		Attribute street = attributes.get(PersonneRepo.ADRESSE);
		Attribute manager = attributes.get(PersonneRepo.MANAGER);
		Attribute ou = attributes.get(PersonneRepo.UNITE_OPERATIONNELLE);
		Attribute jpegPhoto = attributes.get(PersonneRepo.PHOTO);

		try {
			result.setIdentifiant(uid != null ? (String) uid.get() : null);
			result.setNom(cn != null ? (String) cn.get() : null);
			result.setNomDeFamille(sn != null ? (String) sn.get() : null);
			result.setPrenom(givenname != null ? (String) givenname.get() : null);
			result.setMail(mail != null ? (String) mail.get() : null);
			result.setTelephone(telephoneNumber != null ? (String) telephoneNumber.get() : null);
			result.setSite(st != null ? (String) st.get() : null);
			result.setAdresse(street != null ? (String) street.get() : null);
			result.setManager(manager != null ? (String) manager.get() : null);
			result.setUniteOperationnelle(ou != null ? (String) ou.get() : null);
			result.setPhoto(jpegPhoto != null ? new String((byte[]) jpegPhoto.get()) : null);
		}
		catch (NamingException e) {
			throw new IllegalArgumentException(e);
		}

		logger.debug("conversion de {} en {}", attributes, result);

		return result;
	}

	@Override
	public Attributes convertTo(Personne personne, Attributes attributes) {
		Attributes result = new BasicAttributes();

		result.put(PersonneRepo.IDENTIFIANT, personne.getIdentifiant());
		result.put(PersonneRepo.NOM, personne.getNom());
		result.put(PersonneRepo.NOM_DE_FAMILLE, personne.getNomDeFamille());
		result.put(PersonneRepo.PRENOM, personne.getPrenom());
		result.put(PersonneRepo.MAIL, personne.getMail());
		result.put(PersonneRepo.TELEPHONE, personne.getTelephone());
		result.put(PersonneRepo.SITE, personne.getSite());
		result.put(PersonneRepo.ADRESSE, personne.getAdresse());
		result.put(PersonneRepo.MANAGER, personne.getManager());
		result.put(PersonneRepo.UNITE_OPERATIONNELLE, personne.getUniteOperationnelle());
		result.put(PersonneRepo.PHOTO, personne.getPhoto().getBytes());

		logger.debug("conversion de {} en {}", personne, result);

		return result;
	}

}
