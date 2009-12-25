package persistence.daos;

import java.util.List;

import persistence.exceptions.DataAccessLayerException;
import dominio.Casillero;
import dominio.CasilleroNegro;

public interface ICasilleroDao extends IAbstractDao{

	public abstract void create(Casillero casillero)
			throws DataAccessLayerException;

	public abstract void delete(Casillero casillero)
			throws DataAccessLayerException;

	public abstract Casillero find(Long id) throws DataAccessLayerException;

	public abstract void update(Casillero casillero)
			throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Casillero> findAll() throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract Casillero findByNombre(String nombre)
			throws DataAccessLayerException;

	public abstract List<CasilleroNegro> findDesocupado(int x, int y,
			Class clazz);

	public abstract List<CasilleroNegro> findOcupadoOponente(int x, int y,
			String color);

}