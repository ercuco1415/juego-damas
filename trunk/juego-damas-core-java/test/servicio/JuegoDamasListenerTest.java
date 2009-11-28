package servicio;

import org.junit.Test;

import servicios.JuegoDamasListener;

import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerTest {

	@Test
	public void inicio() throws NoHayFichaEnCasilleroException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		
	}

	@Test
	public void testBotMueveACasillero() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
	}

}
