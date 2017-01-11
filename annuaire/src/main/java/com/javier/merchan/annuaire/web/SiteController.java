package com.javier.merchan.annuaire.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javier.merchan.annuaire.domain.metier.entity.Site;
import com.javier.merchan.annuaire.domain.metier.repository.SiteRepo;

@RestController
@RequestMapping(SiteController.PATH_SITES)
public class SiteController {

	public static final String PATH_SITES = "/rest/sites";

	@Autowired
	private SiteRepo siteRepo;

	@RequestMapping("/")
	public List<Site> sites() {
		return siteRepo.findAll();
	}

}
