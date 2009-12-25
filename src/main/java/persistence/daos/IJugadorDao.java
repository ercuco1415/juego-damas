package persistence.daos;

import java.util.List;

import persistence.exceptions.DataAccessLayerException;
import dominio.Jugador;

public interface IJugadorDao extends IAbstractDao{

	public abstract void create(Jugador jugador)
			throws DataAccessLayerException;

	public abstract void delete(Jugador jugador)
			throws DataAccessLayerException;

	public abstract Jugador find(Long id) throws DataAccessLayerException;

	public abstract void update(Jugador jugador)
			throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Jugador> findAll() throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Jugador> filtrarCanales(String nombre, String orden,
			Integer pagina, Integer cantPagina) throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract Jugador findByNombre(String nombreCanal)
			throws DataAccessLayerException;

}