package servicios;

import java.util.ArrayList;
import java.util.List;

import persistence.utils.UtilDBInitializer;
import servicios.utils.ServiceLocator;
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

public class JuegoDamasListener implements IJuegoDamasListener {
	private IObjectPersistenceService objectPersistenceService;
	public JuegoDamasListener() {}

	public IObjectPersistenceService getObjectPersistenceService() {
		return (IObjectPersistenceService) ServiceLocator.getInstance().getService(IObjectPersistenceService.class);
	}

	public void setObjectPersistenceService(
			IObjectPersistenceService objectPersistenceService) {
		this.objectPersistenceService = objectPersistenceService;
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
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#init()
	 */
	public void inizializarBase(){
		UtilDBInitializer.initDropCreate();
	}
	public void init() {
		System.out.println("INICIO DE JUEGO");
		inizializarBase();
		inicializarJugada();
		System.out.println("INICIO DE JUEGO");
	}
	public void inicializarJugada(){
		Tablero tablero = new Tablero();
		Jugador jugadorMaquina = new Maquina();
		Jugador jugadorHumano = new Humano();
		getObjectPersistenceService().guarda(tablero);
		jugadorHumano.poneFichas(tablero.getNegros());
		jugadorMaquina.poneFichas(tablero.getNegros());
		getObjectPersistenceService().guarda(jugadorHumano);
		getObjectPersistenceService().guarda(jugadorMaquina);
		jugadorMaquina.agregarJugador(jugadorHumano);
		jugadorHumano.agregarJugador(jugadorMaquina);
		jugadorMaquina.tenesTurno();
		getObjectPersistenceService().guarda(jugadorHumano);
		getObjectPersistenceService().guarda(jugadorMaquina);
	}
	public boolean moveFicha(String fichaStr, String casilleroStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException,
			CasilleroOcupadoException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		Ficha ficha = getObjectPersistenceService().dameFicha(FichaNegra.class,fichaStr);
		List<CasilleroNegro> casillerosDisp = dameCasillerosDisponibles(fichaStr);
		CasilleroNegro casillero = getObjectPersistenceService().obtenerCasillero(casilleroStr);
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
			ficha.comeFicha(casillerosOcupadosADerecha.get(0));
			ficha.movete(casillero);
			System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
			return true;
		}
		//si los casilleros obtenidos desocupados posteriores a los ocupados contienen al casillero seleccionado a mover
		if(casillerosDesocupadoAIzquierda.contains(casillero)){
			//debo comer la ficha que se encuentra en el casillero anterior a derecha
			ficha.comeFicha(casillerosOcupadosAIzquierda.get(0));
			ficha.movete(casillero);
			System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
			return true;
		}
		ficha.movete(casillero);
		System.out.println("MOVE FICHA: " + fichaStr + " DEL CASILLERO: " + casilleroStr);
		return true;
	}
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#botMueveACasillero()
	 */
	public String botMueveACasillero() {
		System.out.println("MUEVE LA MAQUINA");
		return null;
	}
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#dameCasillerosDisponibles(java.lang.String)
	 */
	public List<CasilleroNegro> dameCasillerosDisponibles(String fichaStr) throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		Ficha ficha=this.obtenerFicha(fichaStr);
		List<CasilleroNegro> casillerosDisponibles = ficha.dameCasillerosDisponibles();
		return casillerosDisponibles;
	}
	private Ficha obtenerFicha(String fichaStr){
		Ficha ficha=null;
		if(fichaStr.contains("fn")){
			ficha = getObjectPersistenceService().dameFicha(FichaNegra.class,fichaStr);
		}
		if(fichaStr.contains("fb")){
			ficha = getObjectPersistenceService().dameFicha(FichaBlanca.class,fichaStr);
		}
		return ficha;
	}
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#dameFichasBlancas()
	 */
	public List<Ficha> dameFichasBlancas(){
		List<Ficha> fichas = new ArrayList<Ficha>();
		Jugador jugador = getObjectPersistenceService().dameJugadorMaquina();
		fichas.addAll(jugador.getFichas());
		return fichas;
	}
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#dameCasillerosNegros()
	 */
	public List<CasilleroNegro> dameCasillerosNegros() {
		Tablero tablero =getObjectPersistenceService().obtenerTablero();
		return tablero.getNegros();
	}
	/* (non-Javadoc)
	 * @see servicios.IJuegoDamasListener#dameFichasNegras()
	 */
	public List<Ficha> dameFichasNegras() {
		List<Ficha> fichas = new ArrayList<Ficha>();
		Jugador jugador = getObjectPersistenceService().dameJugadorHumano();
		fichas.addAll(jugador.getFichas());
		return fichas;
	}
}