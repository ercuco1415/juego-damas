package persistence.daos;

import java.util.List;

import persistence.exceptions.DataAccessLayerException;
import dominio.Tablero;

public interface ITableroDao extends IAbstractDao{

	public abstract void create(Tablero tablero)
			throws DataAccessLayerException;

	public abstract void delete(Tablero tablero)
			throws DataAccessLayerException;

	public abstract Tablero find(Long id) throws DataAccessLayerException;

	public abstract void update(Tablero tablero)
			throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Tablero> findAll() throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Tablero> filtrarCanales(String nombre, String orden,
			Integer pagina, Integer cantPagina) throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract Tablero findByNombre(String nombreCanal)
			throws DataAccessLayerException;

}