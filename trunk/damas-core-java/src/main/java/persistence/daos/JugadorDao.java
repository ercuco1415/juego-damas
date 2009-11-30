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
import dominio.Jugador;

public class JugadorDao extends AbstractDao  {
  
    public JugadorDao() {
        super();
    }

    public void create(Jugador jugador) throws DataAccessLayerException {
        super.save(jugador);
    }

    public void delete(Jugador jugador) throws DataAccessLayerException {
        super.delete(jugador);
    }

    public Jugador find(Long id) throws DataAccessLayerException {
        return (Jugador) super.find(Jugador.class, id);
    }

    public void update(Jugador jugador) throws DataAccessLayerException {
        super.update(jugador);
    }

    @SuppressWarnings("unchecked")
    public List<Jugador> findAll() throws DataAccessLayerException {
        return super.findAll(Jugador.class);
    }
   
    @SuppressWarnings("unchecked")
    public List<Jugador> filtrarCanales(String nombre, String orden, Integer pagina,
        Integer cantPagina) throws DataAccessLayerException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("s0", "%" + nombre + "%");
        String _where = "a.nombre like :s0 ";
        String _orderBy = "ORDER BY  a." + orden;
        return super.findQueryToDelimit(Jugador.class, _where + _orderBy, parametros, pagina,
            cantPagina);
    }


    @SuppressWarnings("unchecked")
    public Jugador findByNombre(String nombreCanal) throws DataAccessLayerException {
    	Jugador unCanal = null;
    	
		Session session = null;
		Transaction tx = null;
		try {
	        Map<String, Object> parametros = new HashMap<String, Object>();
	        parametros.put("pCanal", nombreCanal);
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(" from Canal jugador where jugador.nombre = :pCanal");
			query.setParameter("pCanal", nombreCanal);

			List jugadores = query.list();
		       unCanal = (Jugador)jugadores.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return unCanal;
    }
}
