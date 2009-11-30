package persistence.daos;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;
import dominio.Casillero;
import dominio.CasilleroNegro;
import dominio.Ficha;
import dominio.FichaNegra;

public class CasilleroDao extends AbstractDao  {
  
    public CasilleroDao() {
        super();
    }

    public void create(Casillero casillero) throws DataAccessLayerException {
        super.save(casillero);
    }

    public void delete(Casillero casillero) throws DataAccessLayerException {
        super.delete(casillero);
    }

    public Casillero find(Long id) throws DataAccessLayerException {
        return (Casillero) super.find(Casillero.class, id);
    }

    public void update(Casillero casillero) throws DataAccessLayerException {
        super.update(casillero);
    }

    @SuppressWarnings("unchecked")
    public List<Casillero> findAll() throws DataAccessLayerException {
        return super.findAll(Casillero.class);
    }
    @SuppressWarnings("unchecked")
    public Casillero findByNombre(String nombre) throws DataAccessLayerException {
    	Casillero unCanal = null;
    	
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
		       unCanal = (Casillero)canales.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return unCanal;
    }

	public List<CasilleroNegro> findDisponibles(int x, int y,Class clazz) {
		Session session = null;
		Transaction tx = null;
		List<CasilleroNegro> casilleros=null;
		try {
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("x", new Integer(x));
//			map.put("y", new Integer(y));
//			map.put("ocupado", false);
//			session = HibernateFactory.openSession();
//			tx = session.beginTransaction();
//			String stringHQLFROM = CasilleroNegro.class.getName() + " A  , "  + clazz.getName() + " B " ;
//			String stringHQLWHERE= "A.x = :x  AND A.y = :y  AND  A.ocupado = :ocupado AND B.entityType = " + clazz.getName(); //AND  B.casillero = A.id 
//			casilleros = super.findQuery(stringHQLFROM, stringHQLWHERE, map);
//			tx.commit();
			
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(CasilleroNegro.class).
			add(Property.forName("x").eq(x)).
			add(Property.forName("y").eq(y)).
			add(Property.forName("ocupado").eq(false));
			casilleros = criteria.list();
			tx.commit();
			
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return casilleros;
	}
}
