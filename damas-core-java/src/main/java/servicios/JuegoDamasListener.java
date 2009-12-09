package servicios;

import java.util.ArrayList;
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
			CasilleroOcupadoException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		Ficha ficha = objectPersistenceService.dameFicha(FichaNegra.class,fichaStr);
		List<CasilleroNegro> casillerosDisp = dameCasillerosDisponibles(fichaStr);
		CasilleroNegro casillero = objectPersistenceService.obtenerCasillero(casilleroStr);
		if (!validaCasillero(casillero, casillerosDisp)) {
			return false;
		}
		//obtengo los casilleros que estan ocupados
		CasilleroNegro casilleroActual = (CasilleroNegro) ficha.getCasillero();
		List<CasilleroNegro> casillerosOcupadosADerecha = casilleroActual.obtenerCasillerosOcupadosAdelanteDerecha(ficha.getColor());
		//obtengo los casilleros que estan desocupados posteriores a los ocupados
		List<CasilleroNegro> casillerosDesocupadoADerecha= new ArrayList<CasilleroNegro>();
		for(Casillero casilleroPost: casillerosOcupadosADerecha){
			String color = (ficha.getColor().equals(Ficha.BLANCA))?Ficha.NEGRA:Ficha.BLANCA;
			casillerosDesocupadoADerecha.addAll(casilleroPost.obtenerCasillerosDesocupadosIzquierda(color, false));
		}
		
		//obtengo los casilleros que estan ocupados
		List<CasilleroNegro> casillerosOcupadosAIzquierda= casilleroActual.obtenerCasillerosOcupadosAdelanteIzquierda(ficha.getColor());
		List<CasilleroNegro> casillerosDesocupadoAIzquierda= new ArrayList<CasilleroNegro>();
		//obtengo los casilleros que estan desocupados posteriores a los ocupados
		for(Casillero casilleroPost: casillerosOcupadosAIzquierda){
			String color = (ficha.getColor().equals(Ficha.BLANCA))?Ficha.NEGRA:Ficha.BLANCA;
			casillerosDesocupadoAIzquierda.addAll(casilleroPost.obtenerCasillerosDesocupadosDerecha(color, false));
		}
		//si los casilleros obtenidos desocupados posteriores a los ocupados contienen al casillero seleccionado a mover
		if(casillerosDesocupadoADerecha.contains(casillero)){
			//debo comer la ficha que se encuentra en el casillero anterior a derecha
			CasilleroNegro casilleroNvo = (CasilleroNegro) ficha.comeFicha(casillerosOcupadosADerecha.get(0));
			ficha.movete(casilleroNvo);
			System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
			return true;
		}
		//si los casilleros obtenidos desocupados posteriores a los ocupados contienen al casillero seleccionado a mover
		if(casillerosDesocupadoAIzquierda.contains(casillero)){
			//debo comer la ficha que se encuentra en el casillero anterior a derecha
			CasilleroNegro casilleroNvo = (CasilleroNegro) ficha.comeFicha(casillerosOcupadosAIzquierda.get(0));
			ficha.movete(casilleroNvo);
			System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
			return true;
		}
		ficha.movete(casillero);
		
		System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
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
		System.out.println("COME FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
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
	public List<Ficha> dameFichasBlancas(){
		List<Ficha> fichas = new ArrayList<Ficha>();
		fichas.addAll(this.jugadorMaquina.getFichas());
		return fichas;
	}

	public List<CasilleroNegro> dameCasillerosNegros() {
		return this.tablero.getNegros();
	}

	public List<Ficha> dameFichasNegras() {
		List<Ficha> fichas = new ArrayList<Ficha>();
		fichas.addAll(this.jugadorHumano.getFichas());
		return fichas;
	}
	
	
}