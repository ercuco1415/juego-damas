package persistence;

import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.HibernateProxyHelper;
import org.hibernate.proxy.LazyInitializer;

public class HibernateProxyHelperService {

	public  Object getTargetBehindProxy(final Object obj) {
        Object ret = obj;
        	if (isProxy(obj)) {
        		HibernateProxyHelper.getClassWithoutInitializingProxy((HibernateProxy)obj);
//        		ret = init.getImplementation();
        		
        		HibernateProxy hp =(HibernateProxy)obj;
        		LazyInitializer init = null;
        		init = (LazyInitializer)hp.getHibernateLazyInitializer();
        		ret = init.getImplementation();
        		
        	}
        	
        return ret;
    }
	public  Class getClass(final Object obj) {
        if (obj == null) {
            return null;
        }
        Class clazz = null;
        //return HibernateProxyHelper.getClass(obj);
        try {
			clazz = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return clazz;
    }
    public  boolean isProxy(final Class clazz) {
        return HibernateProxy.class.isAssignableFrom(clazz);
    }
    public  boolean isProxy(final Object obj) {
        return (obj != null) && isProxy(obj.getClass());
    }

}
