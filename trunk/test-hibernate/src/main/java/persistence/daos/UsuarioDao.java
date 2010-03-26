package persistence.daos;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Property;

import persistence.HibernateFactory;
import persistence.exceptions.DataAccessLayerException;
import seguridad.Usuario;

public class UsuarioDao extends AbstractDao  {
  
    public UsuarioDao() {
        super();
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#create(seguridad.Usuario)
	 */
    public void create(Usuario usuario) throws DataAccessLayerException {
        super.save(usuario);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#delete(seguridad.Usuario)
	 */
    public void delete(Usuario usuario) throws DataAccessLayerException {
        super.delete(usuario);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#find(java.lang.Long)
	 */
    public Usuario find(Long id) throws DataAccessLayerException {
        return (Usuario) super.find(Usuario.class, id);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#update(seguridad.Usuario)
	 */
    public void update(Usuario usuario) throws DataAccessLayerException {
        super.update(usuario);
    }

    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#findAll()
	 */
    @SuppressWarnings("unchecked")
    public List<Usuario> findAll() throws DataAccessLayerException {
        return super.findAll(Usuario.class);
    }
    /* (non-Javadoc)
	 * @see persistence.daos.IUsuarioDao#findBy(java.lang.String)
	 */
    @SuppressWarnings("unchecked")
    public List<Usuario> findBy(String username) throws DataAccessLayerException {
    	Session session = null;
		Transaction tx = null;
		List<Usuario> usuarios=null;
		try {

			session = HibernateFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Usuario.class).
			add(Property.forName("username").eq(username));
			usuarios = criteria.list();
			tx.commit();
			
		} catch (HibernateException e) {
			handleException(tx, e);
		} finally {
			HibernateFactory.closeSession(session);
		}
    	
        return usuarios;
    }

	
}
