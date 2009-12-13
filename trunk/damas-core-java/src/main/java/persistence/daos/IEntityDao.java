package persistence.daos;

import java.util.List;

import persistence.exceptions.DataAccessLayerException;
import dominio.Entidad;

public interface IEntityDao extends IAbstractDao{

	public abstract void create(Entidad entity) throws DataAccessLayerException;

	public abstract void delete(Entidad entity) throws DataAccessLayerException;

	public abstract Entidad find(Long id) throws DataAccessLayerException;

	public abstract void update(Entidad entity) throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Entidad> findAll() throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract Entidad findByNombre(String nombre)
			throws DataAccessLayerException;

}