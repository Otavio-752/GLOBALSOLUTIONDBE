package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fiap.dao.UserDao;
import br.com.fiap.model.User;

@Named
@RequestScoped
public class VisitanteBean {

	private Visitante visitante = new User();
	
	@Inject 
	private Visitante Dao dao;
	
	public String save(){
		
		dao.create(getUser());
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Usuário cadastrado com sucesso"));
		
		return "users";
	}
	
	public List<User> getAll(){
		return dao.listAll();
	}
	
	public String login() {
		if(dao.exist(visitante)) return "setups";
		
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "Erro"));
		return "login";
	}

	public Visitante getUser() {
		return visitante;
	}

	public void setUser(Visitante visitante) {
		this.visitante = visitante;
	}



}
