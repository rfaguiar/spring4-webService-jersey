package br.com.livro.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

public class MyApplication extends Application {

	/*@Override
	public Set<Object> getSingletons() {
		
		Set<Object> singletons = new HashSet<Object>();
		//Driver do jettison para gerar JSON
		singletons.add(new JettisonFeature());
		return singletons;
		
	}*/
	
	@Override
	public Map<String, Object> getProperties() {
		
		Map<String, Object> properties = new HashMap<String, Object>();
		//configura o pacote para fazer scan das classes com anota��es REST
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
		
		
	}
}
