package servicios;

import java.util.List;

import persistence.daos.ICasilleroDao;
import persistence.daos.IEntityDao;
import persistence.daos.IFichaDao;
import persistence.daos.IJugadorDao;
import persistence.daos.IPartidoDao;
import persistence.daos.ITableroDao;
import servicios.utils.ServiceLocator;
import dominio.CasilleroNegro;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Maquina;
import dominio.Partido;
import dominio.Posicion;
import dominio.Tablero;

public class ObjectPersistenceService implements IObjectPersistenceService  {
	

	
	public IEntityDao getEntityDao() {
		
		return  (IEntityDao) ServiceLocator.getInstance().getService(IEntityDao.class);
	}
	public IPartidoDao getPartidoDao() {
		
		return  (IPartidoDao) ServiceLocator.getInstance().getService(IPartidoDao.class);
	}	
	public IFichaDao getFichaDao() {
		return (IFichaDao) ServiceLocator.getInstance().getService(IFichaDao.class);
	}
	public ITableroDao getTableroDao() {
		return (ITableroDao) ServiceLocator.getInstance().getService(ITableroDao.class);
	}
	public ICasilleroDao getCasilleroDao() {
		return (ICasilleroDao) ServiceLocator.getInstance().getService(ICasilleroDao.class);
	}
	public IJugadorDao getJugadorDao() {
		return (IJugadorDao) ServiceLocator.getInstance().getService(IJugadorDao.class);
	}
	public void guarda(Entidad entidad) {
		if(entidad.getId() != null){
			getEntityDao().update(entidad);
			return;
		}
		
		getEntityDao().create(entidad);
		return;
	}

	public Ficha dameFicha(Class clazz, String idEntity) {
		
		return (Ficha) getFichaDao().findByIdEntity(clazz,idEntity);
	}
	
	public Ficha obtenerFicha(CasilleroNegro casilleroNegro) {
		Ficha ficha = null;
		
		ficha = getFichaDao().findBlanca(casilleroNegro);
		if(ficha == null){
			ficha = getFichaDao().findNegra(casilleroNegro);
		}
		return ficha;
	}
	
	public Tablero obtenerTablero() {
		return getTableroDao().findAll().iterator().next();
	}
	public List<CasilleroNegro> obtenerCasillerosDisponibles(Posicion pos,Class clazz) {
		List<CasilleroNegro> listResult = getCasilleroDao().findDesocupado( pos.getX(),pos.getY(),clazz);
		
		return listResult;
	}
	public List<CasilleroNegro> obtenerCasillerosOcupadosOponente(Posicion pos,String color) {
		List<CasilleroNegro> listResult = getCasilleroDao().findOcupadoOponente(pos.getX(),pos.getY(),color);
		return listResult;
	}
	public CasilleroNegro obtenerCasillero(String casilleroStr) {
		return (CasilleroNegro) getCasilleroDao().findByIdEntity(CasilleroNegro.class, casilleroStr);
	}
	public Jugador dameJugadorMaquina() {
		return (Jugador) getJugadorDao().findByIdEntity(Maquina.class, "MAQUINA");
	}
	public Jugador dameJugadorHumano() {
		return (Jugador) getJugadorDao().findByIdEntity(Maquina.class, "CHOMA");
	}
	public Partido obtenerPartido(String idEntity) {
		return (Partido) getPartidoDao().findByIdEntity(Partido.class, idEntity);
	}
	
	
}
