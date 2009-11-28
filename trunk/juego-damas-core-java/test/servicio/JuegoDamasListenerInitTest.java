package servicio;

import org.junit.Test;

import servicios.JuegoDamasListener;
import excepciones.NoHayFichaEnCasilleroException;

public class JuegoDamasListenerInitTest {

	@Test
	public void inicio() throws NoHayFichaEnCasilleroException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		
	}


}
