package br.com.livro.domain;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

@Component
public class CarroDAO extends HibernateDAO<Carro> {

	public CarroDAO() {
		super(Carro.class);
	}

	public Carro getCarroById(Long id){
		return super.get(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> findByName(String name){
		Query q = getSession().createQuery("from Carro where lower (nome) like (?)");
		q.setString(0, "%" + name + "%");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> findByTipo(String tipo){
		Query q = getSession().createQuery("from Carro where tipo=?");
		q.setString(0, tipo);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> getCarros(){
		Query q = getSession().createQuery("from Carro");
		return q.list();
	}
		
	public void save(Carro c){
		super.save(c);		
	}
	
	public boolean delete(Long id){
		Carro c = get(id);
		delete(c);
		return true;
	}
}
