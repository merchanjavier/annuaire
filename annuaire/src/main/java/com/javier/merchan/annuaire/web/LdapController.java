package com.javier.merchan.annuaire.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;
import com.javier.merchan.annuaire.domain.ldap.repository.PersonneRepo;

@RestController
@RequestMapping(LdapController.PATH_LDAP)
public class LdapController {

	public static final String PATH_LDAP = "/rest/ldap";

	@Autowired
	private PersonneRepo personneRepo;

	@RequestMapping("/personnes")
	public List<Personne> personnes() {
		return personneRepo.trouverTous();
	}

	@RequestMapping("/personnes/{uid}")
	public Personne obtenirPersonne(@PathVariable("uid") String uid) {
		Personne personne = personneRepo.trouverParUid(uid);
		return personne;
	}

	@RequestMapping("/personnes/{uid}/mail/{mail}")
	public Personne mailPersonne(@PathVariable("uid") String uid, @PathVariable("mail") String mail) {
		Personne personne = personneRepo.trouverParUid(uid);
		if (personne != null) {
			personne.setMail(mail);
			personneRepo.mettreAJour(personne);
		}
		return personne;
	}
}
