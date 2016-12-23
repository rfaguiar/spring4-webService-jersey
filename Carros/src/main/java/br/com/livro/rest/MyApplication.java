package br.com.livro.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class MyApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		
		Set<Object> singletons = new HashSet<Object>();
		//Suporte ao file upload
		singletons.add(new MultiPartFeature());
		return singletons;
		
	}
	
	@Override
	public Map<String, Object> getProperties() {
		
		Map<String, Object> properties = new HashMap<String, Object>();
		//configura o pacote para fazer scan das classes com anota��es REST
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
		
		
	}
}
