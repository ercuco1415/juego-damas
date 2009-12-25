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

public class TableroDao extends AbstractDao implements ITableroDao  {
  
    public TableroDao() {
        super();
    }

    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#create(dominio.Tablero)
	 */
    public void create(Tablero tablero) throws DataAccessLayerException {
        super.save(tablero);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#delete(dominio.Tablero)
	 */
    public void delete(Tablero tablero) throws DataAccessLayerException {
        super.delete(tablero);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#find(java.lang.Long)
	 */
    public Tablero find(Long id) throws DataAccessLayerException {
        return (Tablero) super.find(Tablero.class, id);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#update(dominio.Tablero)
	 */
    public void update(Tablero tablero) throws DataAccessLayerException {
        super.update(tablero);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#findAll()
	 */
    @SuppressWarnings("unchecked")
    public List<Tablero> findAll() throws DataAccessLayerException {
        return super.findAll(Tablero.class);
    }
   
    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#filtrarCanales(java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
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


    /* (non-Javadoc)
	 * @see persistence.daos.ITableroDao#findByNombre(java.lang.String)
	 */
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
