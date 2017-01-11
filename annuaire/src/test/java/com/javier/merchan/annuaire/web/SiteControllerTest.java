package com.javier.merchan.annuaire.web;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SiteControllerTest {

	public static final String JSON_SITES = "[{\"id\":1,\"libelle\":\"Paris\"},{\"id\":2,\"libelle\":\"Londres\"}]";

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	public void doit_recuperer_tous_les_sites() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get(SiteController.PATH_SITES + "/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().string(equalTo(JSON_SITES)));
	}

}
