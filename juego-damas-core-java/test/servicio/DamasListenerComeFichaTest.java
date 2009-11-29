package servicio;

import org.junit.Test;

import servicios.JuegoDamasListener;

import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class DamasListenerComeFichaTest {

	@Test
	public void testComeFicha() throws NoHayFichaEnCasilleroException, FormatoCasilleroException, CasilleroOcupadoException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		System.out.println(damasListener.dameCasillerosDisponibles("fn13").toString());
		
		damasListener.moveFicha("fb11", "x10y7");
		System.out.println(damasListener.dameCasillerosDisponibles("fb11"));

		damasListener.moveFicha("fn13", "x7y4");
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
		
		damasListener.moveFicha("fb11", "x9y6");
		System.out.println(damasListener.dameCasillerosDisponibles("fb11"));
		
		damasListener.moveFicha("fn13", "x8y5");
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
		
		damasListener.comeFicha("fb11","x8y5");
		System.out.println(damasListener.dameCasillerosDisponibles("fb11"));
		
	}

}
