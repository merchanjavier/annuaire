package com.javier.merchan.annuaire.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.javier.merchan.annuaire.domain.metier.entity.UniteOperationnelle;
import com.javier.merchan.annuaire.domain.metier.repository.UniteOperationnelleRepo;

@RestController
@RequestMapping(UniteOperationnelleController.PATH_UNITES_OPERATIONNELLES)
public class UniteOperationnelleController {

	public static final String PATH_UNITES_OPERATIONNELLES = "/rest/unitesOperationnelles";

	@Autowired
	private UniteOperationnelleRepo uniteOperationnelleRepo;

	@RequestMapping("/")
	public List<UniteOperationnelle> unitesOperationnelles() {
		return uniteOperationnelleRepo.findAll();
	}

}
