package br.com.fiap.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;

public class VisitanteDao {
	
	@Inject
	private EntityManager manager;

	public void create(Visitante visitante) {
		
		manager.getTransaction().begin();
		manager.persist(visitante);
		manager.getTransaction().commit();
		
		manager.clear();
	}
	
	public List<Visitante> listAll() {
		
		TypedQuery<Visitante> query = 
				manager.createQuery("SELECT u FROM User u", Visitante.class);
		
		return query.getResultList();
		
	}

	public boolean exist(Visitante visitante) {
		
		String jpql = "SELECT u FROM User u WHERE email=:email AND password=:password";
		TypedQuery<Visitante> query = manager.createQuery(jpql, Visitante.class);
		query.setParameter("email", visitante.getEmail());
		query.setParameter("password", Visitante.getPassword());
		
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	