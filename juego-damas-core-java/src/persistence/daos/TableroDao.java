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
import dominio.Tablero;

public class TableroDao extends AbstractDao  {
  
    public TableroDao() {
        super();
    }

    public void create(Tablero tablero) throws DataAccessLayerException {
        super.save(tablero);
    }

    public void delete(Tablero tablero) throws DataAccessLayerException {
        super.delete(tablero);
    }

    public Tablero find(Long id) throws DataAccessLayerException {
        return (Tablero) super.find(Tablero.class, id);
    }

    public void update(Tablero tablero) throws DataAccessLayerException {
        super.update(tablero);
    }

    @SuppressWarnings("unchecked")
    public List<Tablero> findAll() throws DataAccessLayerException {
        return super.findAll(Tablero.class);
    }
   
    @SuppressWarnings("unchecked")
    public List<Tablero> filtrarCanales(String nombre, String orden, Integer pagina,
        Integer cantPagina) throws DataAccessLayerException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("s0", "%" + nombre + "%");
        String _where = "a.nombre like :s0 ";
        String _orderBy = "ORDER BY  a." + orden;
        return super.findQueryToDelimit(Tablero.class, _where + _orderBy, parametros, pagina,
            cantPagina);
    }


    @SuppressWarnings("unchecked")
    public Tablero findByNombre(String nombreCanal) throws DataAccessLayerException {
    	Tablero unCanal = null;
    	
		Session session = null;
		Transaction tx = null;
		try {
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("pCanal", nombreCanal);
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(" from Canal canal where canal.nombre = :pCanal");
			query.setParameter("pCanal", nombreCanal);

			List canales = query.list();
		       unCanal = (Tablero)canales.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return unCanal;
    }
}
