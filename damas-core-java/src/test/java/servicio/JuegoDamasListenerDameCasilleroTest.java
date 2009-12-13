package servicio;

import org.junit.Test;

import servicios.IJuegoDamasListener;
import servicios.utils.ServiceLocator;
import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerDameCasilleroTest {

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		IJuegoDamasListener damasListener =  (IJuegoDamasListener) ServiceLocator.getInstance().getService(IJuegoDamasListener.class);
		damasListener.init();
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
	}

}
