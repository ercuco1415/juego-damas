package ar.gob.gcba.loys.postulante.commons.domain;

import java.util.List;

import core.domain.state.machine.Entidad;

public class Postulante extends Entidad{

	private static final long serialVersionUID = 318326263069000828L;
	private String apellido;
	private String nombre;
	private List<Autenticacion> autenticaciones;
	private CurriculumVitae curriculumVitae;
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Autenticacion> getAutenticaciones() {
		return autenticaciones;
	}
	public void setAutenticaciones(List<Autenticacion> autenticaciones) {
		this.autenticaciones = autenticaciones;
	}
	public CurriculumVitae getCurriculumVitae() {
		return curriculumVitae;
	}
	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}
	
}
