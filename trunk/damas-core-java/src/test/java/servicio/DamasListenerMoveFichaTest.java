package servicio;

import static org.junit.Assert.*;

import org.junit.Test;

import servicios.JuegoDamasListener;
import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;

public class DamasListenerMoveFichaTest {

	@Test
	public void testDameCasillerosDisponibles() throws NoHayFichaEnCasilleroException, FormatoCasilleroException, CasilleroOcupadoException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		JuegoDamasListener damasListener =  new JuegoDamasListener();
		damasListener.init();
		String antes = damasListener.dameCasillerosDisponibles("fn13").toString();
		damasListener.moveFicha("fn13", "x5y4");
		assertEquals("[x6y5, x4y5]",damasListener.dameCasillerosDisponibles("fn13").toString());
		assertEquals("[x7y4, x5y4]",antes);
		System.out.println("despues: " + damasListener.dameCasillerosDisponibles("fn13"));
		System.out.println("antes: " + antes);
	}

}
