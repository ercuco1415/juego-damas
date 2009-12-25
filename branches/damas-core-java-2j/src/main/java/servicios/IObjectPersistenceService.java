package servicios;

import java.util.List;

import dominio.CasilleroNegro;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Partido;
import dominio.Posicion;
import dominio.Tablero;

public interface IObjectPersistenceService {

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

	public abstract Partido obtenerPartido(String idEntity);

}