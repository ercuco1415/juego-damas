package servicio;

import java.util.Date;

import org.junit.Test;

import servicios.IJuegoDamasListener;
import servicios.utils.ServiceLocator;
import excepciones.FormatoCasilleroException;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerDameCasilleroTest {

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException {
		IJuegoDamasListener damasListener =  (IJuegoDamasListener) ServiceLocator.getInstance().getService(IJuegoDamasListener.class);
		damasListener.inicializar("05:30");
		System.out.println(damasListener.dameCasillerosDisponibles("fn13"));
	}

}
