package persistence.daos;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;


public abstract class AbstractDao {

	private static final Logger logger = Logger.getLogger(AbstractDao.class);

	protected void save(Object obj) throws DataAccessLayerException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
	}
	protected void update(Object obj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}

	}
	protected void delete(Serializable obj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		
	}
	protected Object find(Class clazz, Long id) {
		Object obj = null;

		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			obj = session.load(clazz, id);
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		
		return obj;
	}
	@SuppressWarnings("unchecked")
	protected List findQuery(String from, String where, Map<String, Object> hashMap) {
		List objects = new ArrayList();
		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			query = session.createQuery(" FROM " + from + " WHERE " + where);
			query = setQueryParameters(query, hashMap);
			List list = query.list();
			tx.commit();
			Iterator pairs = list.iterator();
			while (pairs.hasNext()) {
				Object[] pair = (Object[])pairs.next();
				objects.add(pair[0]);
			}
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		return objects;
	}
	protected Query setQueryParameters(Query query, Map<String, Object> hashMap) {
		for (Iterator i = hashMap.entrySet().iterator(); i.hasNext();) {
			Map.Entry e = (Map.Entry)i.next();
			if (((String)e.getKey()).substring(0, 1).equals("a")) {
				query.setParameter((String)e.getKey(), e.getValue());
			}
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	protected List findQueryWhere(Class clazz, String where, Map<String, Object> hashMap) {
		List objects = new ArrayList();
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(" from " + clazz.getSimpleName() + " a " + " where " + 
					where);
			query = setQueryParameters(query, hashMap);
			List list = query.list();

			if (list != null) {
				objects.addAll(list);
			}
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		
		return objects;
	}
	@SuppressWarnings("unchecked")
	protected List findQueryToDelimit(Class clazz, String where, Map<String, Object> hashMap, 
			Integer minimo, Integer maximo) {
		ArrayList resultadoReturn = new ArrayList();
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(" from " + clazz.getSimpleName() + " a " + " where " + 
					where).setFirstResult(minimo).setMaxResults(maximo);
			query = setQueryParameters(query, hashMap);
			List resultadoQuery = query.list();
			if (resultadoQuery != null) {
				resultadoReturn.addAll(resultadoQuery);
			}
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		
		return resultadoReturn;
	}

	@SuppressWarnings("unchecked")
	protected List findAll(Class clazz) {
		List objects = new ArrayList();
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			tx.commit();
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
		
		return objects;
	}

	protected void handleException(Transaction tx, HibernateException e) 
			throws DataAccessLayerException {
		HibernateFactory.rollback(tx);

		logger.error("Ocurrio un error: " + e.getMessage());
		e.printStackTrace();
		throw new DataAccessLayerException(e);
	}
	


}
