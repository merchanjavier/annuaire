package com.javier.merchan.annuaire.init.spring.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Configuration des paramètre URL et Base du client Spring LDAP
 * 
 * @author Javier MERCHAN
 */
@Configuration
public class ConfigurationClientLdap {

	@Autowired
	Environment environnementSpring;

	@Bean
	public LdapContextSource contextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl(environnementSpring.getRequiredProperty("ldap.url"));
		contextSource.setBase(environnementSpring.getRequiredProperty("ldap.base"));
		return contextSource;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(contextSource());
	}
}
