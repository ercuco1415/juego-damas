package servicio;

import java.util.Date;

import org.junit.Test;

import servicios.IJuegoDamasListener;
import servicios.utils.ServiceLocator;
import excepciones.CasilleroOcupadoException;
import excepciones.FormatoCasilleroException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;

public class DamasListenerComeFichaTest {

	@Test
	public void testComeFicha() throws NoHayFichaEnCasilleroException, FormatoCasilleroException, CasilleroOcupadoException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException {
		IJuegoDamasListener damasListener =  (IJuegoDamasListener) ServiceLocator.getInstance().getService(IJuegoDamasListener.class);
		damasListener.inicializar("05:30");
		System.out.println(damasListener.dameCasillerosDisponibles("fn13").toString());
		
		damasListener.moveFicha("fb11", "x10y7");
		System.out.println(damasListener.dameCasillerosDisponibles("fb11"));

		damasListener.moveFicha("fn13", "x7y4");
		System.out.println("disponibles fb11" +damasListener.dameCasillerosDisponibles("fb11"));
		System.out.println("disponibles fn13" + damasListener.dameCasillerosDisponibles("fn13"));
		
		damasListener.moveFicha("fb11", "x9y6");
		
		damasListener.moveFicha("fn13", "x8y5");
		String resultado =  damasListener.dameCasillerosDisponibles("fb11").toString();
		System.out.println("disponibles fn13" +damasListener.dameCasillerosDisponibles("fn13"));
		System.out.println("disponibles fb11" +resultado);
//		damasListener.moveFicha("fb11","x7y4");
//		System.out.println(damasListener.dameCasillerosDisponibles("fb11"));
		//retorna [x7y6, x10y5]
		//debe retornar x7y6, x10y7
	}

}
