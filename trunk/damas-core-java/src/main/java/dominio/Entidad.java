package dominio;

import java.io.Serializable;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;

public abstract class Entidad implements Serializable {

	private static final long serialVersionUID = -4003897191290483394L;
	private String idEntity;
	private Long id;
	private String entityType;

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public abstract String getEntityType();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdEntity() {
		return idEntity;
	}

	public void setIdEntity(String idEntity) {
		this.idEntity = idEntity;
	}

	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass().equals(obj.getClass())) {
			Entidad other = (Entidad) obj;
			if (getId().equals(other.getId()))
				return true;
		} else if (obj instanceof HibernateProxy) {
			if (HibernateProxyHelper.getClassWithoutInitializingProxy(obj)
					.equals(this.getClass())) {
				Entidad other = (Entidad) obj;
				if (getId().equals(other.getId()))
					return true;
			}
		}

		return false;
	}
}
