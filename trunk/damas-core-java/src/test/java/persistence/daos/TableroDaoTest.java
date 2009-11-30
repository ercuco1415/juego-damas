package persistence.daos;

import org.junit.Test;

import dominio.Tablero;


public class TableroDaoTest {

	@Test
	public void testCreaTablero(){
		TableroDao tableroDao = new TableroDao();
		Tablero tablero= new Tablero();
		tableroDao.create(tablero);
	}
	
	
}
