package dominio;

import org.junit.Test;

import excepciones.CasilleroOcupadoException;
import excepciones.NoExisteCasilleroDisponibleException;
import excepciones.NoHayFichaEnCasilleroException;
import excepciones.NoPuedoComerFichaException;
import excepciones.NoTieneFichaContrarioException;


public class TableroTest extends AbstractTestJugar{
	@Test
	public void init(){
		
		System.out.println(jugadorHumano.getFichas());
		System.out.println(jugadorHumano.getFichas().size());
		System.out.println(jugadorMaquina.getFichas());
		System.out.println(jugadorMaquina.getFichas().size());
	}
	@Test
	public  void moverFicha() throws CasilleroOcupadoException{
		Ficha ficha =jugadorMaquina.getFichas().get(jugadorMaquina.getFichas().size()-1);
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
		jugadorMaquina.finTurno();
		
	}
	@Test
	public void mostrarCasillerosLibres() throws CasilleroOcupadoException{
		Ficha ficha =jugadorMaquina.getFichas().get(jugadorMaquina.getFichas().size()-1);
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
	}
	
	@Test
	public void comerFicha() throws CasilleroOcupadoException, NoHayFichaEnCasilleroException, NoExisteCasilleroDisponibleException, NoTieneFichaContrarioException, NoPuedoComerFichaException{
		Casillero casillero = new CasilleroNegro();
		casillero.x = 1;
		casillero.y = 3;
		Ficha ficha =jugadorMaquina.dameFicha(casillero );
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasilleroDerecha(tablero.getNegros()));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
		jugadorMaquina.finTurno();
		
		casillero.x = 6;
		casillero.y = 8;
		ficha =jugadorHumano.dameFicha(casillero);
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasilleroDerecha(tablero.getNegros()));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
		jugadorHumano.finTurno();
		
		
		casillero.x = 2;
		casillero.y = 4;
		ficha =jugadorMaquina.dameFicha(casillero );
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasilleroDerecha(tablero.getNegros()));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
		jugadorMaquina.finTurno();
		
		casillero.x = 5;
		casillero.y = 7;
		ficha =jugadorHumano.dameFicha(casillero);
		System.out.println("casillero anterior: " + ficha.getCasillero());
		System.out.println("casillero a mover: " + ficha.dameCasillerosDisponibles(tablero.getNegros()).get(0));
		ficha.movete(ficha.dameCasilleroDerecha(tablero.getNegros()));
		System.out.println("ficha movida" + ficha);
		System.out.println(tablero.getNegros());
		jugadorHumano.finTurno();
		
		casillero.x = 3;
		casillero.y = 5;
		ficha =jugadorMaquina.dameFicha(casillero );
		Casillero casilleroContrincante = ficha.dameCasilleroDerecha(tablero.getNegros());
		System.out.println("FICHA CONTRINCANTE: " + casilleroContrincante.ficha);
		ficha.comeFicha(casilleroContrincante,tablero.getNegros());
		System.out.println("FICHAS HUMANO: " + jugadorHumano.getFichas().size());
		System.out.println(casilleroContrincante.ficha);
		jugadorMaquina.finTurno();
	}
	
}
