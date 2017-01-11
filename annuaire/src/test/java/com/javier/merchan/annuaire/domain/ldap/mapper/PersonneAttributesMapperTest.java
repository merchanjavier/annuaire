package com.javier.merchan.annuaire.domain.ldap.mapper;

import static org.mockito.Mockito.verify;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.javier.merchan.annuaire.domain.ldap.entity.Personne;

@RunWith(MockitoJUnitRunner.class)
public class PersonneAttributesMapperTest {

	@Mock
	private PersonneConverter converter;
	@InjectMocks
	private PersonneAttributesMapper personneAttributesMapper;

	@Test
	public void doit_convertir_attributes_en_personne() throws NamingException {
		// GIVEN
		Attributes attributes = new BasicAttributes();

		// WHEN
		personneAttributesMapper.mapFromAttributes(attributes);

		// THEN
		verify(converter).convertFrom(attributes);
	}

	@Test
	public void doit_convertir_personne_en_attributes() {
		// GIVEN
		Personne personne = new Personne();

		// WHEN
		personneAttributesMapper.mapFromPersonne(personne);

		// THEN
		verify(converter).convertTo(personne);
	}
}
