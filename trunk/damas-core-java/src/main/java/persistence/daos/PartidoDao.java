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

public class PartidoDao extends AbstractDao implements ITableroDao, IPartidoDao  {
  
    public PartidoDao() {
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
   
    @SuppressWarnings("unchecked")
    public Tablero findByNombre(String nombre) throws DataAccessLayerException {
    	Tablero unCanal = null;
    	
		Session session = null;
		Transaction tx = null;
		try {
	       
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return unCanal;
    }

	public List<Tablero> filtrarCanales(String nombre, String orden,
			Integer pagina, Integer cantPagina) throws DataAccessLayerException {
		throw new UnsupportedOperationException("under construction");
	}
}
