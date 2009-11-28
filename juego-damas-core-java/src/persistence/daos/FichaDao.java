package persistence.daos;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;
import dominio.Ficha;

public class FichaDao extends AbstractDao  {
  
    public FichaDao() {
        super();
    }

    public void create(Ficha ficha) throws DataAccessLayerException {
        super.save(ficha);
    }

    public void delete(Ficha ficha) throws DataAccessLayerException {
        super.delete(ficha);
    }

    public Ficha find(Long id) throws DataAccessLayerException {
        return (Ficha) super.find(Ficha.class, id);
    }

    public void update(Ficha ficha) throws DataAccessLayerException {
        super.update(ficha);
    }

    @SuppressWarnings("unchecked")
    public List<Ficha> findAll() throws DataAccessLayerException {
        return super.findAll(Ficha.class);
    }
   
   
	public Ficha findByIdEntity(Class clazz,String idEntity) {
		Ficha ficha = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(clazz);
			criteria.add(Restrictions.like("idEntity", idEntity));
			List canales = criteria.list();
			ficha = (Ficha)canales.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return ficha;
	}
}
