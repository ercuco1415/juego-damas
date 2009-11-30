package persistence.daos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;
import dominio.Entidad;

public class EntityDao extends AbstractDao  {
  
    public EntityDao() {
        super();
    }

    public void create(Entidad entity) throws DataAccessLayerException {
    	super.save(entity);
    }

    public void delete(Entidad entity) throws DataAccessLayerException {
        super.delete(entity);
    }

    public Entidad find(Long id) throws DataAccessLayerException {
        return (Entidad) super.find(Entidad.class, id);
    }

    public void update(Entidad entity) throws DataAccessLayerException {
        super.update(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Entidad> findAll() throws DataAccessLayerException {
        return super.findAll(Entidad.class);
    }
    @SuppressWarnings("unchecked")
    public Entidad findByNombre(String nombre) throws DataAccessLayerException {
    	Entidad unCanal = null;
    	
		Session session = null;
		Transaction tx = null;
		try {
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("pCanal", nombre);
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(" from Canal canal where canal.nombre = :pCanal");
			query.setParameter("pCanal", nombre);

			List canales = query.list();
		       unCanal = (Entidad)canales.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return unCanal;
    }
}
