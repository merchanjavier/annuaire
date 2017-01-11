package com.javier.merchan.annuaire.init.unboidid;

import java.io.File;
import java.net.URISyntaxException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Service;
import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldap.sdk.LDAPException;

/**
 * Conteneur d'un serveur LDAP en mémoire fait par Unboundid
 * 
 * @author Javier MERCHAN
 */
@Service
public class ServeurLdap {

	private static final int LDAP_PORT = 389;
	private static final String BASE_DN = "dc=maboite,dc=com";
	private static final String DATA_LDIF_FILE = "data.ldif";

	public InMemoryDirectoryServer ds;

	@PostConstruct
	public void init() throws LDAPException, URISyntaxException {
		InMemoryDirectoryServerConfig config = new InMemoryDirectoryServerConfig(BASE_DN);
		config.setListenerConfigs(new InMemoryListenerConfig("listener", null, LDAP_PORT, null, null, null));

		ds = new InMemoryDirectoryServer(config);
		ds.importFromLDIF(true, getLdifFile());
		ds.startListening();
	}

	private String getLdifFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(DATA_LDIF_FILE).getFile());
		return file.getPath();
	}

	@PreDestroy
	public void shutdown() {
		ds.shutDown(true);
	}
}
