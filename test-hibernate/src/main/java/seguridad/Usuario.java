package seguridad;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Usuario implements Serializable {

	private static final long serialVersionUID = 655609790022158686L;
	private String username;
	private String password;
	private Long id;
	private Set<Perfil> perfiles= new HashSet<Perfil>();
	
	
	
	public Set<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean checkPassword(String password){
		//TODO agregar hash de password.
		if(password.equals(this.password)){
			return true;
		}
		return false;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
