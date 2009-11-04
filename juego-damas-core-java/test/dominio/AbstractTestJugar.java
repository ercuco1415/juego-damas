package dominio;

import org.junit.Before;

public class AbstractTestJugar {

	protected Tablero tablero = new Tablero();
	protected Jugador jugadorMaquina = new Maquina();
	protected Jugador jugadorHumano = new Humano();
	@Before
	public void setUp() throws Exception {
		jugadorMaquina.poneFichas(tablero.getNegros());
		jugadorHumano.poneFichas(tablero.getNegros());
		jugadorMaquina.agregarJugador(jugadorHumano);
		jugadorHumano.agregarJugador(jugadorMaquina);
		jugadorMaquina.setTablero(tablero);
		jugadorHumano.setTablero(tablero);
		jugadorMaquina.tenesTurno();
	}

	
	
}
