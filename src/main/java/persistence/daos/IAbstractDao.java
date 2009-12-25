package persistence.daos;

import dominio.Entidad;

public interface IAbstractDao {

	public abstract Entidad findByIdEntity(Class clazz, String idEntity);

}