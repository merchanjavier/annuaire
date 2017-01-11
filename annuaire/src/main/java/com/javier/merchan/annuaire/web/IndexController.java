package com.javier.merchan.annuaire.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private static final String INDEX_HTML = "index.html";

	@RequestMapping("/")
	public String index() {
		return INDEX_HTML;
	}
}
