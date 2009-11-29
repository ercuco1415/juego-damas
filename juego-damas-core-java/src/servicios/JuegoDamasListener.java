package servicios;

import java.util.List;

import persistence.utils.UtilDBInitializer;
import dominio.Casillero;
import dominio.CasilleroNegro;
import dominio.Ficha;
import dominio.FichaBlanca;
import dominio.FichaNegra;
import dominio.Humano;
import dominio.Jugador;
import dominio.Maquina;
import dominio.Tablero;
import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;

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
		Ficha ficha=this.obtenerFicha(fichaStr);
		List<CasilleroNegro> casillerosDisponibles = ficha.dameCasillerosDisponibles();
		return casillerosDisponibles;
	}

	public void comeFicha(String fichaStr, String casilleroStr) {
		Ficha ficha=this.obtenerFicha(fichaStr);
		CasilleroNegro casillero = objectPersistenceService.obtenerCasillero(casilleroStr);
		try {
			CasilleroNegro casilleroNvo = (CasilleroNegro) ficha.comeFicha(casillero);
			ficha.movete(casilleroNvo);
		} catch (NoExisteCasilleroDisponibleException e) {
			throw new RuntimeException(e);
		} catch (NoTieneFichaContrarioException e) {
			throw new RuntimeException(e);
		} catch (NoPuedoComerFichaException e) {
			throw new RuntimeException(e);
		} catch (CasilleroOcupadoException e) {
			throw new RuntimeException(e);
		}
		
	}
	private Ficha obtenerFicha(String fichaStr){
		Ficha ficha=null;
		if(fichaStr.contains("fn")){
			ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		}
		if(fichaStr.contains("fb")){
			ficha = objectPersistenceService.dameFicha(FichaBlanca.class,fichaStr);
		}
		return ficha;
	}

	
	
}