package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;
import br.com.livro.util.RegexUtil;
import br.com.livro.util.ServletUtil;

@WebServlet("/carros/*")
public class CarrosServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private CarroService carroService = new CarroService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if(id != null){
			//informou id
			Carro carro = carroService.getCarro(id);
			if(carro != null){
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				ServletUtil.writeJSON(resp, json);
			}else{
				resp.sendError(404, "Carro n�o encontrado");
			}
		}else{
			//lista de carros
			List<Carro> carros = carroService.getCarros();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);
			ServletUtil.writeJSON(resp, json);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Carro carro = getCarroFromRequest(req);
		
		carroService.save(carro);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(carro);
		ServletUtil.writeJSON(resp, json);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String requestUri = req.getRequestURI();
		Long id = RegexUtil.matchId(requestUri);
		if(id != null){
			carroService.delete(id);
			Response r = Response.ok("Carro excluido com sucesso");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(r);
			ServletUtil.writeJSON(resp, json);
		}else{
			resp.sendError(404, "URL inv�lida");
		}
		
	}

	private Carro getCarroFromRequest(HttpServletRequest req) {
		Carro c = new Carro();
		String id= req.getParameter("id");
		if(id != null){
			c = carroService.getCarro(Long.parseLong(id));
		}
		c.setNome(req.getParameter("nome"));
		c.setDesc(req.getParameter("descricao"));
		c.setUrlFoto(req.getParameter("url_foto"));
		c.setUrlVideo(req.getParameter("url_video"));
		c.setLatitude(req.getParameter("latitude"));
		c.setLongitude(req.getParameter("longitude"));
		c.setTipo(req.getParameter("tipo"));
		return c;
	}


}
