package servicios;

import java.util.List;

import persistence.daos.CasilleroDao;
import persistence.daos.FichaDao;
import persistence.daos.ICasilleroDao;
import persistence.daos.IEntityDao;
import persistence.daos.IFichaDao;
import persistence.daos.IJugadorDao;
import persistence.daos.ITableroDao;
import persistence.daos.JugadorDao;
import servicios.utils.ServiceLocator;
import dominio.CasilleroNegro;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Maquina;
import dominio.Posicion;
import dominio.Tablero;

public class ObjectPersistenceService implements IObjectPersistenceService  {
	
	private IEntityDao entityDao;
	private IFichaDao fichaDao;
	private ITableroDao tableroDao;
	private ICasilleroDao casilleroDao;
	private IJugadorDao jugadorDao;
	
	
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#getEntityDao()
	 */
	public IEntityDao getEntityDao() {
		
		return  (IEntityDao) ServiceLocator.getInstance().getService(IEntityDao.class);
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#setEntityDao(persistence.daos.IEntityDao)
	 */
	public void setEntityDao(IEntityDao entityDao) {
		this.entityDao = entityDao;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#getFichaDao()
	 */
	public IFichaDao getFichaDao() {
		return (IFichaDao) ServiceLocator.getInstance().getService(IFichaDao.class);
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#setFichaDao(persistence.daos.FichaDao)
	 */
	public void setFichaDao(FichaDao fichaDao) {
		this.fichaDao = fichaDao;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#getTableroDao()
	 */
	public ITableroDao getTableroDao() {
		return (ITableroDao) ServiceLocator.getInstance().getService(ITableroDao.class);
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#setTableroDao(persistence.daos.ITableroDao)
	 */
	public void setTableroDao(ITableroDao tableroDao) {
		this.tableroDao = tableroDao;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#getCasilleroDao()
	 */
	public ICasilleroDao getCasilleroDao() {
		return (ICasilleroDao) ServiceLocator.getInstance().getService(ICasilleroDao.class);
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#setCasilleroDao(persistence.daos.CasilleroDao)
	 */
	public void setCasilleroDao(CasilleroDao casilleroDao) {
		this.casilleroDao = casilleroDao;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#getJugadorDao()
	 */
	public IJugadorDao getJugadorDao() {
		return (IJugadorDao) ServiceLocator.getInstance().getService(IJugadorDao.class);
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#setJugadorDao(persistence.daos.JugadorDao)
	 */
	public void setJugadorDao(JugadorDao jugadorDao) {
		this.jugadorDao = jugadorDao;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#guarda(dominio.Entidad)
	 */
	public void guarda(Entidad entidad) {
		if(entidad.getId() != null){
			getEntityDao().update(entidad);
			return;
		}
		
		getEntityDao().create(entidad);
		return;
	}

	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#dameFicha(java.lang.Class, java.lang.String)
	 */
	public Ficha dameFicha(Class clazz, String idEntity) {
		
		return (Ficha) getFichaDao().findByIdEntity(clazz,idEntity);
	}
	
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerFicha(dominio.CasilleroNegro)
	 */
	public Ficha obtenerFicha(CasilleroNegro casilleroNegro) {
		Ficha ficha = null;
		
		ficha = getFichaDao().findBlanca(casilleroNegro);
		if(ficha == null){
			ficha = getFichaDao().findNegra(casilleroNegro);
		}
		return ficha;
	}
	
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerTablero()
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerTablero()
	 */
	public Tablero obtenerTablero() {
		return getTableroDao().findAll().iterator().next();
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillerosDisponibles(dominio.Posicion, java.lang.Class)
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillerosDisponibles(dominio.Posicion, java.lang.Class)
	 */
	public List<CasilleroNegro> obtenerCasillerosDisponibles(Posicion pos,Class clazz) {
		List<CasilleroNegro> listResult = getCasilleroDao().findDesocupado( pos.getX(),pos.getY(),clazz);
		
		return listResult;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillerosOcupadosOponente(dominio.Posicion, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillerosOcupadosOponente(dominio.Posicion, java.lang.String)
	 */
	public List<CasilleroNegro> obtenerCasillerosOcupadosOponente(Posicion pos,String color) {
		List<CasilleroNegro> listResult = getCasilleroDao().findOcupadoOponente(pos.getX(),pos.getY(),color);
		return listResult;
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillero(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#obtenerCasillero(java.lang.String)
	 */
	public CasilleroNegro obtenerCasillero(String casilleroStr) {
		return (CasilleroNegro) getCasilleroDao().findByIdEntity(CasilleroNegro.class, casilleroStr);
	}
	
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#dameJugadorMaquina()
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#dameJugadorMaquina()
	 */
	public Jugador dameJugadorMaquina() {
		return (Jugador) getJugadorDao().findByIdEntity(Maquina.class, "MAQUINA");
	}
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#dameJugadorHumano()
	 */
	/* (non-Javadoc)
	 * @see servicios.IObjectPersistenceService#dameJugadorHumano()
	 */
	public Jugador dameJugadorHumano() {
		return (Jugador) getJugadorDao().findByIdEntity(Maquina.class, "CHOMA");
	}
	
}
