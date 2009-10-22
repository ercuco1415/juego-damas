package dominio;

import org.junit.Before;

public class AbstractTestJugar {

	protected Tablero tablero = new Tablero();
	protected AbstractJugador jugadorMaquina = new JugadorMaquina();
	protected AbstractJugador jugadorHumano = new JugadorHumano();
	@Before
	public void setUp() throws Exception {
		jugadorMaquina.poneFichas(tablero.getNegros());
		jugadorHumano.poneFichas(tablero.getNegros());
		jugadorMaquina.agregarJugador(jugadorHumano);
		jugadorHumano.agregarJugador(jugadorMaquina);
		jugadorMaquina.tenesTurno();
	}

	
	
}
