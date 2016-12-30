package test;

import static org.junit.Assert.*;

import java.util.Base64;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.ResponseWithURL;
import br.com.livro.rest.GsonMessageBodyHandler;

public class RestTest {

	@Test
	public void testGetCarroId() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);		
		client.register(GsonMessageBodyHandler.class);
		
		String URL = "http://localhost:8080/Carros/rest";		
		WebTarget target = client.target(URL).path("carros/11");		
		Response response = target.request(MediaType.APPLICATION_JSON).get();
		
		int status = response.getStatus();		
		Carro c = response.readEntity(Carro.class);
		
		assertEquals(200, status);
		assertEquals("Ferrari FF", c.getNome());
	}
	
	//@Test
	public void testDeleteCarroId() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);		
		client.register(GsonMessageBodyHandler.class);
		
		String URL = "http://localhost:8080/Carros/rest";	
		WebTarget target = client.target(URL).path("carros/12");		
		Response response = target.request(MediaType.APPLICATION_JSON).delete();
		
		int status = response.getStatus();				
		assertEquals(200, status);
		
		br.com.livro.domain.Response s = response.readEntity(br.com.livro.domain.Response.class);
		
		assertEquals("OK", s.getStatus());
		assertEquals("Carro deletado com sucesso", s.getMsg());
	}
	
	@Test
	public void testPostFormParams() {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);		
		client.register(GsonMessageBodyHandler.class);
		
		String base64 = Base64.getEncoder().encodeToString("Ricardo Lecheta".getBytes());
		
		Form form = new Form();
		form.param("fileName", "nome.xt");
		form.param("base64", base64);
				
		String URL = "http://localhost:8080/Carros/rest";		
		WebTarget target = client.target(URL).path("carros/postFotoBase64");		
		Entity<Form> entity = Entity.entity(form,  MediaType.APPLICATION_FORM_URLENCODED);
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
				
		assertEquals(200, response.getStatus());
		
		ResponseWithURL r = response.readEntity(ResponseWithURL.class);
		
		assertEquals("OK", r.getStatus());
		assertEquals("Arquivo recebido com sucesso", r.getMsg());
	}
	
	@Test
	public void testSalvarNovoCarro() throws Exception {
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);		
		client.register(GsonMessageBodyHandler.class);
		
		Carro c = new Carro();
		c.setNome("Novo Carro");
		
		String URL = "http://localhost:8080/Carros/";	
		WebTarget target = client.target(URL).path("/rest/carros");		
		Entity<Carro> entity = Entity.entity(c, MediaType.APPLICATION_JSON);
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity , Response.class);
		
		int status = response.getStatus();				
		assertEquals(200, status);
		
		br.com.livro.domain.Response s = response.readEntity(br.com.livro.domain.Response.class);
		
		assertEquals("OK", s.getStatus());
		assertEquals("Carro salvo com sucesso", s.getMsg());
	}

}
