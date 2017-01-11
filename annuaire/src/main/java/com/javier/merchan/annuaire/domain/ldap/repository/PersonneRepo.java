package com.javier.merchan.annuaire.domain.ldap.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;

/**
 * Repository LDAP pour Personne
 * 
 * @author Javier MERCHAN
 */
@Repository
public interface PersonneRepo {

	String TELEPHONE = "telephoneNumber";
	String UNITE_OPERATIONNELLE = "ou";
	String MANAGER = "manager";
	String ADRESSE = "street";
	String SITE = "st";
	String MAIL = "mail";
	String PRENOM = "givenname";
	String NOM_DE_FAMILLE = "sn";
	String NOM = "cn";
	String IDENTIFIANT = "uid";
	String PHOTO = "jpegPhoto";

	/**
	 * Obtenir toutes les personnes à partir du serveur LDAP
	 * 
	 * @return
	 */
	List<Personne> trouverTous();

	/**
	 * Trouver une personne par son identifiant
	 * 
	 * @param uid
	 * @return
	 */
	Personne trouverParUid(String uid);

	/**
	 * Mettre à jour une personne existante avec de nouvelles informations
	 * 
	 * @param personne
	 */
	void mettreAJour(Personne personne);

}