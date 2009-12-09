package persistence.daos;

import java.util.List;

import org.junit.Test;

import dominio.CasilleroNegro;
import dominio.Ficha;


public class CasilleroDaoTest {
	
	@Test
	public void testOponente(){
		CasilleroDao dao = new CasilleroDao();
		List<CasilleroNegro> casilleros = dao.findOcupadoOponente(2,1,Ficha.BLANCA);
		System.out.println(casilleros);
	}
	
	@Test
	public void testNoOponente(){
		CasilleroDao dao = new CasilleroDao();
		List<CasilleroNegro> casilleros =dao.findOcupadoOponente(1,8,  Ficha.BLANCA);
		System.out.println(casilleros);
	}
}
