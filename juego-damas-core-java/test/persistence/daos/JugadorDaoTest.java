package persistence.daos;

import org.junit.Before;
import org.junit.Test;

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
		
		TableroDao tableroDao = new TableroDao();
		Tablero tablero = tableroDao.findAll().iterator().next();
		
		JugadorDao daoJugador = new JugadorDao();
		
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
