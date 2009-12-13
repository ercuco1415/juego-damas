package persistence.daos;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;
import dominio.CasilleroNegro;
import dominio.Ficha;
import dominio.FichaBlanca;
import dominio.FichaNegra;

public class FichaDao extends AbstractDao implements IFichaDao  {
  
    public FichaDao() {
        super();
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#create(dominio.Ficha)
	 */
    public void create(Ficha ficha) throws DataAccessLayerException {
        super.save(ficha);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#delete(dominio.Ficha)
	 */
    public void delete(Ficha ficha) throws DataAccessLayerException {
        super.delete(ficha);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#find(java.lang.Long)
	 */
    public Ficha find(Long id) throws DataAccessLayerException {
        return (Ficha) super.find(Ficha.class, id);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#update(dominio.Ficha)
	 */
    public void update(Ficha ficha) throws DataAccessLayerException {
        super.update(ficha);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#findAll()
	 */
    @SuppressWarnings("unchecked")
    public List<Ficha> findAll() throws DataAccessLayerException {
        return super.findAll(Ficha.class);
    }
   
   
	

	/* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#findBlanca(dominio.CasilleroNegro)
	 */
	public Ficha findBlanca(CasilleroNegro casilleroNegro) {
		Ficha ficha = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FichaBlanca.class).
			createAlias("casillero","cas").
			add(Property.forName("cas.idEntity").eq(casilleroNegro.getIdEntity())).
			add(Property.forName("entityType").eq(FichaBlanca.class.getName()));
			List fichas = criteria.list();
			if(fichas != null && !fichas.isEmpty()) ficha = (Ficha)fichas.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return ficha;
	}
	/* (non-Javadoc)
	 * @see persistence.daos.IFichaDao#findNegra(dominio.CasilleroNegro)
	 */
	public Ficha findNegra(CasilleroNegro casilleroNegro) {
		Ficha ficha = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FichaNegra.class).
			createAlias("casillero","cas").
			add(Property.forName("cas.idEntity").eq(casilleroNegro.getIdEntity())).
			add(Property.forName("entityType").eq(FichaNegra.class.getName()));
			List fichas = criteria.list();
			if(fichas != null && !fichas.isEmpty()) ficha = (Ficha)fichas.get(0);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return ficha;
	}
}
