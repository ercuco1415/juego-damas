package servicio;

import org.junit.Test;

import servicios.IJuegoDamasListener;
import servicios.utils.ServiceLocator;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerInitTest {

	@Test
	public void inicio() throws NoHayFichaEnCasilleroException {
		IJuegoDamasListener damasListener =  (IJuegoDamasListener) ServiceLocator.getInstance().getService(IJuegoDamasListener.class);
		damasListener.inicializar("00:30:31");
		
	}


}
