package persistence.daos;

import org.junit.Before;
import org.junit.Test;

import servicios.utils.ServiceLocator;

import dominio.Ficha;
import dominio.Humano;
import dominio.Jugador;
import dominio.Maquina;
import dominio.Tablero;


public class JugadorDaoTest {

	@Before
	public void setUp() {
		
		CreateDBTest createDBTest = new CreateDBTest();
		createDBTest.test();
		TableroDaoTest daoTest = new TableroDaoTest();
		daoTest.testCreaTablero();
	}
	@Test
	public void testCrea(){
		
		ITableroDao tableroDao = (ITableroDao) ServiceLocator.getInstance().getService(ITableroDao.class);
		Tablero tablero = tableroDao.findAll().iterator().next();
		
		IJugadorDao daoJugador =(IJugadorDao) ServiceLocator.getInstance().getService(IJugadorDao.class);
		
		Jugador jugador= new Humano();
		jugador.poneFichas(tablero.getNegros());
		daoJugador.create(jugador);
		Jugador jugador2= new Maquina();
		jugador2.poneFichas(tablero.getNegros());
		daoJugador.create(jugador2);
		
		jugador.agregarJugador(jugador2);
		jugador2.agregarJugador(jugador);
		
		daoJugador.update(jugador);
		daoJugador.update(jugador2);
	}
	
	
}
