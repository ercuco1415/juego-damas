package servicio;

import org.junit.Test;

import servicios.JuegoDamasListener;

import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerDameCasilleroTest {

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
	}

}
