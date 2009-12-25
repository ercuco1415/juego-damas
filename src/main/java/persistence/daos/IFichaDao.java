package persistence.daos;

import java.util.List;

import persistence.exceptions.DataAccessLayerException;
import dominio.CasilleroNegro;
import dominio.Ficha;

public interface IFichaDao extends IAbstractDao{

	public abstract void create(Ficha ficha) throws DataAccessLayerException;

	public abstract void delete(Ficha ficha) throws DataAccessLayerException;

	public abstract Ficha find(Long id) throws DataAccessLayerException;

	public abstract void update(Ficha ficha) throws DataAccessLayerException;

	@SuppressWarnings("unchecked")
	public abstract List<Ficha> findAll() throws DataAccessLayerException;

	public abstract Ficha findBlanca(CasilleroNegro casilleroNegro);

	public abstract Ficha findNegra(CasilleroNegro casilleroNegro);


}