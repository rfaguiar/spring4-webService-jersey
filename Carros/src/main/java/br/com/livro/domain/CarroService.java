package br.com.livro.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CarroService {

	@Autowired
	private CarroDAO db;
	
	//busca todos carros
	public List<Carro> getCarros(){
			List<Carro> carros = db.getCarros();
			return carros;
	}
	
	//busca um carro pelo id
	public Carro getCarro(Long id){
			return db.getCarroById(id);
	}
	
	//Deleta o carro pelo id
	@Transactional(rollbackFor=Exception.class)
	public boolean delete(Long id){
			return db.delete(id);
	}
	
	//salva ou atualiza o carro
	@Transactional(rollbackFor=Exception.class)
	public boolean save(Carro carro){
			db.saveOrUpdate(carro);
			return true;
	}
	
	//busca carros pelo nome
	public List<Carro> findByName(String name){
			return db.findByName(name);
	}
	
	//busca carros pelo tipo
	public List<Carro> findByTipo(String tipo){
			return db.findByTipo(tipo);
	} 
}
