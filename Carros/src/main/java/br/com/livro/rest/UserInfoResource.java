package br.com.livro.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("userInfo")
public class UserInfoResource {

	@Context
	SecurityContext securityContext;
	
	@GET
	public String userInfo(){
		if(this.securityContext == null){
			throw new NotAuthorizedException("Acesso não autorizado");
		}
		String name = securityContext.getUserPrincipal().getName();
		if(securityContext.isUserInRole("admin")){
			return "Você é um Administrador: " + name;
		}
		if(securityContext.isUserInRole("user")){
			return "Você é um Usúario: " + name;
		}
		return "Nenhum dos dois";
	}
	
	@GET
	@Path("/somenteUser")
	@RolesAllowed("user")
	public String somenteUser(){
		return "Parabéns você é um Usúario";
	}
	
	@GET
	@Path("/somenteAdmin")
	@RolesAllowed("admin")
	public String somenteAdmin(){
		return "Parabéns você é um Administrador";
	}
	
	@GET
	@Path("/userOuAdmin")
	@RolesAllowed({"user","admin"})
	public String userOuAdmin(){
		return "Parabéns você é um Usúario ou Administrador";
	}
}
