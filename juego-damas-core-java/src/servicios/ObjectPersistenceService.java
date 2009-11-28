package servicios;

import java.util.List;

import persistence.HibernateProxyHelperService;
import persistence.daos.EntityDao;
import persistence.daos.FichaDao;
import dominio.Entidad;
import dominio.Ficha;
import dominio.Jugador;

public class ObjectPersistenceService {
	EntityDao entityDao = new EntityDao();
	FichaDao fichaDao = new FichaDao();
	HibernateProxyHelperService helperService = new HibernateProxyHelperService();
	public void guarda(Entidad entidad) {
		Entidad resultEntidad=null;
		if(entidad.getId() != null){
			resultEntidad = entityDao.find(entidad.getId());
		}
		if(resultEntidad != null){
			entityDao.update(entidad);
			return;
		}
		entityDao.create(entidad);
		return;
	}
	public Ficha dameFicha(Class clazz, String idEntity) {
		
		Ficha ficha = fichaDao.findByIdEntity(clazz,idEntity);
		Jugador jugador = (Jugador) helperService.getTargetBehindProxy(ficha.getJugador());
		List fichas = jugador.getFichas();
		if (!fichas.contains(ficha)) {
			return null;
		} else {
			return (Ficha) fichas.get(fichas.indexOf(ficha));
		}
	}
}
