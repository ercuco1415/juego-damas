package servicios;

import java.util.List;

import persistence.daos.CasilleroDao;
import persistence.daos.FichaDao;
import persistence.daos.ICasilleroDao;
import persistence.daos.IEntityDao;
import persistence.daos.IFichaDao;
import persistence.daos.IJugadorDao;
import persistence.daos.ITableroDao;
import persistence.daos.JugadorDao;
import dominio.CasilleroNegro;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Posicion;
import dominio.Tablero;

public interface IObjectPersistenceService {

	public abstract IEntityDao getEntityDao();

	public abstract void setEntityDao(IEntityDao entityDao);

	public abstract IFichaDao getFichaDao();

	public abstract void setFichaDao(FichaDao fichaDao);

	public abstract ITableroDao getTableroDao();

	public abstract void setTableroDao(ITableroDao tableroDao);

	public abstract ICasilleroDao getCasilleroDao();

	public abstract void setCasilleroDao(CasilleroDao casilleroDao);

	public abstract IJugadorDao getJugadorDao();

	public abstract void setJugadorDao(JugadorDao jugadorDao);

	public abstract void guarda(Entidad entidad);

	public abstract Ficha dameFicha(Class clazz, String idEntity);

	public abstract Ficha obtenerFicha(CasilleroNegro casilleroNegro);

	public abstract Tablero obtenerTablero();

	public abstract List<CasilleroNegro> obtenerCasillerosDisponibles(
			Posicion pos, Class clazz);

	public abstract List<CasilleroNegro> obtenerCasillerosOcupadosOponente(
			Posicion pos, String color);

	public abstract CasilleroNegro obtenerCasillero(String casilleroStr);

	public abstract Jugador dameJugadorMaquina();

	public abstract Jugador dameJugadorHumano();

}