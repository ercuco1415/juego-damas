package core.domain.state.machine;

import java.io.Serializable;
import java.util.Date;

public class Entidad implements Serializable{

	private static final long serialVersionUID = -5666247956620884432L;
	private Long ID;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String operadorModificacion;
	private String operadorCreacion;
	public Long getID() {
		return ID;
	}
	public void setID(Long id) {
		ID = id;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getOperadorModificacion() {
		return operadorModificacion;
	}
	public void setOperadorModificacion(String operadorModificacion) {
		this.operadorModificacion = operadorModificacion;
	}
	public String getOperadorCreacion() {
		return operadorCreacion;
	}
	public void setOperadorCreacion(String operadorCreacion) {
		this.operadorCreacion = operadorCreacion;
	}
	
	
}
