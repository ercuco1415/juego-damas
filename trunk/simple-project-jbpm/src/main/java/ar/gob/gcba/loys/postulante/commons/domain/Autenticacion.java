package ar.gob.gcba.loys.postulante.commons.domain;

import core.domain.state.machine.Entidad;

public class Autenticacion extends Entidad{

	private static final long serialVersionUID = 4037972416742404680L;
	private String username;
	private String token;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
