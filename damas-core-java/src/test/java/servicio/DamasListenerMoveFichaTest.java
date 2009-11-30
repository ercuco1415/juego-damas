package servicio;

import org.junit.Test;

import servicios.JuegoDamasListener;

import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class DamasListenerMoveFichaTest {

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException, CasilleroOcupadoException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		String antes = damasListener.dameCasillerosDisponibles("fn13").toString();
		damasListener.moveFicha("fn13", "x5y4");
		System.out.println("despues: " + damasListener.dameCasillerosDisponibles("fn13"));
		System.out.println("antes: " + antes);
	}

}
