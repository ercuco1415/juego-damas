package seguridad;

import java.io.Serializable;


public class Perfil  implements Serializable{
	private static final long serialVersionUID = -4506943969277240153L;
	private String nombre;
	private String recurso;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass().equals(obj.getClass())) {
			Perfil other = (Perfil) obj;
			if (this.getNombre().equals(other.getNombre()))
				return true;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return this.getNombre().hashCode();
	}
}
