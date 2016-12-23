package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;

public class CarroTest {
	
	private CarroService carroService;
	
	@Before
	public void setUp() throws Exception{
		carroService = (CarroService) SpringUtil.getInstance().getBean(CarroService.class);
	}

	@Test
	public void testeListaCarros() {
		List<Carro> carros = carroService.getCarros();
		assertNotNull(carros);
		//valida se encontroou algo
		assertTrue(carros.size() > 0);
		//valida se encontrou o tucker
		Carro tucker = carroService.findByName("Tucker 1948").get(0);
		assertEquals("Tucker 1948", tucker.getNome());
		//valida se encontrou a ferrari
		Carro ferrari = carroService.findByName("Ferrari FF").get(0);
		assertEquals("Ferrari FF", ferrari.getNome());
		//valida se encontrou bugatti
		Carro bugatti = carroService.findByName("Bugatti Veyron").get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}
	
	@Test
	public void testSalvarDeletarCarro() throws Exception {
		Carro c = new Carro();
		c.setNome("Teste");
		c.setDesc("teste Desc");
		c.setUrlFoto("url foto aqui");
		c.setUrlVideo("url video aqui");
		c.setLatitude("lat");
		c.setLongitude("lng");
		c.setTipo("tipo");
		carroService.save(c);
		//id do carro salvo
		Long id = c.getId();
		assertNotNull(id);
		//busca no banco de dados para confirmar que o carro foi salvo
		c = carroService.getCarro(id);
		assertEquals("Teste", c.getNome());
		assertEquals("teste Desc", c.getDesc());
		assertEquals("url foto aqui", c.getUrlFoto());
		assertEquals("url video aqui", c.getUrlVideo());
		assertEquals("lat", c.getLatitude());
		assertEquals("lng", c.getLongitude());
		assertEquals("tipo", c.getTipo());
		//atualiza o carro
		c.setNome("Teste UPDATE");
		carroService.save(c);
		//busca o carro novamente (deve estar atualizado)
		c = carroService.getCarro(id);
		assertEquals("Teste UPDATE", c.getNome());
		//deleta o carro
		carroService.delete(id);
		//busca novamente
		c = carroService.getCarro(id);
		//desta vez o carro nao existe mais
		assertNull(c);
	}

}
