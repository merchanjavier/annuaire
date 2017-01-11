package com.javier.merchan.annuaire.domain.ldap.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;
import com.javier.merchan.annuaire.domain.ldap.repository.PersonneRepo;

@RunWith(MockitoJUnitRunner.class)
public class PersonneConverterTest {

	@InjectMocks
	private PersonneConverter personneConverter;

	String identifiant = "12345";
	String nom = "Javier Merchan";
	String nomDeFamille = "Merchan";
	String prenom = "Javier";
	String mail = "merchanjavier@gmail.com";
	String telephone = "0123456789";
	String site = "Paris";
	String adresse = "21 rue du clos Feuquières 75015 PARIS";
	String manager = "Manager";
	String uniteOperationnelle = "Craft Team";
	String photo = "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";

	@Test
	public void doit_convertir_des_attributes_en_personne() {
		// GIVEN
		Attributes attributes = creerAttributes();

		// WHEN
		Personne actual = personneConverter.convertFrom(attributes);

		// THEN
		assertThat(actual).isEqualTo(creerPersonne());
	}

	@Test
	public void doit_convertir_personne_en_attributes() {
		// GIVEN
		Personne personne = creerPersonne();

		// WHEN
		Attributes actual = personneConverter.convertTo(personne);

		// THEN
		assertThat(actual).isEqualTo(creerAttributes());
	}

	private Personne creerPersonne() {
		Personne personne = new Personne();
		personne.setIdentifiant(identifiant);
		personne.setNom(nom);
		personne.setNomDeFamille(nomDeFamille);
		personne.setPrenom(prenom);
		personne.setMail(mail);
		personne.setTelephone(telephone);
		personne.setSite(site);
		personne.setAdresse(adresse);
		personne.setManager(manager);
		personne.setUniteOperationnelle(uniteOperationnelle);
		personne.setPhoto(photo);
		return personne;
	}

	private Attributes creerAttributes() {
		Attributes attributes = new BasicAttributes();
		attributes.put(PersonneRepo.IDENTIFIANT, identifiant);
		attributes.put(PersonneRepo.NOM, nom);
		attributes.put(PersonneRepo.NOM_DE_FAMILLE, nomDeFamille);
		attributes.put(PersonneRepo.PRENOM, prenom);
		attributes.put(PersonneRepo.MAIL, mail);
		attributes.put(PersonneRepo.TELEPHONE, telephone);
		attributes.put(PersonneRepo.SITE, site);
		attributes.put(PersonneRepo.ADRESSE, adresse);
		attributes.put(PersonneRepo.MANAGER, manager);
		attributes.put(PersonneRepo.UNITE_OPERATIONNELLE, uniteOperationnelle);
		attributes.put(PersonneRepo.PHOTO, photo.getBytes());
		return attributes;
	}
}
