package servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

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

	private boolean validaCasillero(Casillero casillero, List<Casillero> casillerosDisp) {
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
		
		jugadorHumano.poneFichas(tablero.getNegros());
		jugadorMaquina.poneFichas(tablero.getNegros());
		jugadorMaquina.agregarJugador(jugadorHumano);
		jugadorHumano.agregarJugador(jugadorMaquina);
		jugadorMaquina.tenesTurno();
		
		objectPersistenceService.guarda(tablero);
		objectPersistenceService.guarda(jugadorHumano);
		objectPersistenceService.guarda(jugadorMaquina);
		
	}

	public boolean moveFicha(String fichaStr, String casilleroStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException,
			CasilleroOcupadoException {
		Ficha ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		List<Casillero> casillerosDisp = dameCasillerosDisponibles(fichaStr);
		Casillero casillero = new CasilleroNegro();
		casillero.setIdEntity(casilleroStr);
		if (!validaCasillero(casillero, casillerosDisp)) {
			return false;
		}
		Casillero casilleroAM = casillerosDisp.get(casillerosDisp
				.indexOf(casillero));
		if (!validaNoSeElCasilleroPropio(casilleroAM, ficha)) {
			return false;
		}
		ficha.movete(casilleroAM);
		return true;
	}

	public String botMueveACasillero() {
		return null;
	}

	public List<Casillero> dameCasillerosDisponibles(String fichaStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		
		Ficha ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		Predicate pred = new Predicate() {
			public boolean evaluate(Object arg0) {
				Casillero casillero = (Casillero) arg0;
				if (!casillero.isOcupada()) {
					return true;
				}
				return false;
			}
		};
		Collection reCollection = CollectionUtils.select(ficha
				.dameCasillerosDisponibles(), pred);
		return new ArrayList<Casillero>(reCollection);
	}

	
	
}