package persistence;



import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import persistence.exceptions.DataAccessLayerException;



public class HibernateFactory {
	private static SessionFactory sessionFactory;
	private static final Logger logger = Logger.getLogger(HibernateFactory.class);


	/**
	 * Constructs a new Singleton SessionFactory
	 * 
	 * @return
	 * @throws HibernateException
	 */
	/*
	public static SessionFactory buildSessionFactory() throws HibernateException {
		if (sessionFactory != null) {
			closeFactory();
		}
		return configureSessionFactory();
	}
	*/


	/**
	 * Builds a SessionFactory, if it hasn't been already.
	 */
	public static SessionFactory buildIfNeeded() throws DataAccessLayerException {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		try {
			return configureSessionFactory();
		} catch (HibernateException e) {
			logger.error("Ocurrio un error: " + e.getMessage());
			e.printStackTrace();

			throw new DataAccessLayerException(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public static Session openSession() throws HibernateException {
		return buildIfNeeded().openSession();
	}
	

	public static void closeFactory() {
		if (sessionFactory != null) {
			try {
				sessionFactory.close();
			} catch (HibernateException ignored) {
				logger.error("No se pudo cerrar el sessionFactory", ignored);
			}
		}
	}


	public static void closeSession(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ignored) {
				logger.error("No se pudo cerrar la sesion", ignored);
			}
		}
	}


	public static void rollback(Transaction tx) {
		try {
			if (tx != null) {
				tx.rollback();
			}
		} catch (HibernateException ignored) {
			logger.error("No se pudo hacer el rollback", ignored);
		}
	}


	/**
	 * @return
	 * @throws HibernateException
	 */
	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
		sessionFactory = configuration.buildSessionFactory();
		return sessionFactory;
	}


}