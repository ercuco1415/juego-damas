package servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import persistence.utils.UtilDBInitializer;
import dominio.Casillero;
import dominio.CasilleroNegro;
import dominio.Ficha;
import dominio.FichaNegra;
import dominio.Humano;
import dominio.Jugador;
import dominio.Maquina;
import dominio.Tablero;
import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListener {

	protected Tablero tablero;
	protected Jugador jugadorMaquina;
	protected Jugador jugadorHumano;
	private ObjectPersistenceService objectPersistenceService= new ObjectPersistenceService();
	public JuegoDamasListener() {
		tablero = new Tablero();
		jugadorMaquina = new Maquina();
		jugadorHumano = new Humano();
	}

	private boolean validaNoSeElCasilleroPropio(Casillero casilleroAM,
			Ficha ficha) {
		if (ficha.getCasillero().equals(casilleroAM)) {
			return false;
		}
		return true;
	}

	private boolean validaCasillero(Casillero casillero, List<CasilleroNegro> casillerosDisp) {
		if (casillerosDisp == null || casillerosDisp.isEmpty()) {
			return false;
		}
		if (!casillerosDisp.contains(casillero)) {
			return false;
		}
		return true;
	}

	
	public void init() {
		
		UtilDBInitializer.initDropCreate();
		objectPersistenceService.guarda(tablero);
		
		jugadorHumano.poneFichas(tablero.getNegros());
		jugadorMaquina.poneFichas(tablero.getNegros());
		objectPersistenceService.guarda(jugadorHumano);
		objectPersistenceService.guarda(jugadorMaquina);
		
		jugadorMaquina.agregarJugador(jugadorHumano);
		jugadorHumano.agregarJugador(jugadorMaquina);
		jugadorMaquina.tenesTurno();
		
		objectPersistenceService.guarda(jugadorHumano);
		objectPersistenceService.guarda(jugadorMaquina);
		
	}

	public boolean moveFicha(String fichaStr, String casilleroStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException,
			CasilleroOcupadoException {
		Ficha ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		List<CasilleroNegro> casillerosDisp = dameCasillerosDisponibles(fichaStr);
		CasilleroNegro casillero = objectPersistenceService.obtenerCasillero(casilleroStr);
		if (!validaCasillero(casillero, casillerosDisp)) {
			return false;
		}
		
		ficha.movete(casillero);
		return true;
	}

	public String botMueveACasillero() {
		return null;
	}

	public List<CasilleroNegro> dameCasillerosDisponibles(String fichaStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		
		Ficha ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		List<CasilleroNegro> casillerosDisponibles = ficha.dameCasillerosDisponibles();
		return casillerosDisponibles;
	}

	
	
}