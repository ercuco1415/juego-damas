package persistence.daos;

import org.junit.Test;

import servicios.utils.ServiceLocator;

import dominio.Tablero;


public class TableroDaoTest {

	@Test
	public void testCreaTablero(){
		ITableroDao tableroDao = (ITableroDao) ServiceLocator.getInstance().getService(ITableroDao.class);
		Tablero tablero= new Tablero();
		tableroDao.create(tablero);
	}
	
	
}
