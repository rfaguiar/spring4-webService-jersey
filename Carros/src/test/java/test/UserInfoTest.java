package test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import br.com.livro.rest.GsonMessageBodyHandler;

public class UserInfoTest {
	
	private String URL = "http://localhost:8080/Carros/rest/userInfo";

	@Test
	public void testUserInfoLoginLivro() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		
		client.register(HttpAuthenticationFeature.basic("livro", "livro123"));
		client.register(GsonMessageBodyHandler.class);
		
		WebTarget target = client.target(URL);
		String s = target.request().get(String.class);
		
		assertEquals("Você é um Usúario: livro", s);
	}
	
	@Test
	public void testUserInfoLoginAdmin() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		
		client.register(HttpAuthenticationFeature.basic("admin", "admin123"));
		client.register(GsonMessageBodyHandler.class);
		
		WebTarget target = client.target(URL);
		String s = target.request().get(String.class);
		
		assertEquals("Você é um Administrador: admin", s);
	}
	
	@Test
	public void testUserInfoAcessoNegado() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		
		client.register(HttpAuthenticationFeature.basic("xxx", "yyy"));
		client.register(GsonMessageBodyHandler.class);
		
		WebTarget target = client.target(URL);
		Response response = target.request().get();
		
		assertEquals(401, response.getStatus());
	}
}
