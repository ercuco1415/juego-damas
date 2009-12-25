package servicios.utils;

import org.apache.hivemind.Registry;
import org.apache.hivemind.impl.RegistryBuilder;
import org.apache.log4j.Logger;

/**
 * Acts as a HiveMind wrapper
 *
 * @author dfeist
 */
public final class ServiceLocator {
    //~ Static fields/initializers -----------------------------------------------------------------
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ServiceLocator.class);

    /**
     * singleton instance
     */
    private static final ServiceLocator instance = new ServiceLocator();

    //~ Instance fields ----------------------------------------------------------------------------

    private Registry hivemindRegistry = RegistryBuilder.constructDefaultRegistry();

    //~ Constructors -------------------------------------------------------------------------------

    /**
     * Private constructor to ensure singleton
     *
     */
    private ServiceLocator() {
        // not implemented
    }

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Obtains ServiceLocator singleton instance.
     *
     * @return ServiceLocator.
     */
    public static ServiceLocator getInstance() {
        if (logger.isDebugEnabled()) {
            logger.debug("getInstance() - start");
        }

        //Acá había una implementación incompleta del idioma "Double-Checked Locking" para
        //evitar el costo de sincronizar la clase.
        //Sin embargo, aún corrigiéndola por una implementación completa está demostrado
        //que en java no funciona. Sólo hay 2 alternativas: 1) sincronizar a nivel de clase o 
        //2) si la variable no cambia, ponerla static final e inicializarla cuando la clase se carga.
        //la deventaja de esto es perder el lazy-load, pero las ventajas en este caso (elimina el costo de 
        //sincronización) son superiores. 
        //Mas datos:
        //http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
        //http://www-106.ibm.com/developerworks/java/library/j-dcl.html?loc=j
        if (logger.isDebugEnabled()) {
            logger.debug("getInstance() - end");
        }
        return instance;
    }

    /**
     * Obtains a service from the registry. Typically, what's returned is a proxy,
     * but that's irrelevant to the caller, which simply will invoke methods
     * of the service interface.
     *
     * @param serviceId the fully qualified id of the service to obtain
     * @param serviceInterface the class to which the service will be cast
     * @return the service
     */
    public Object getService(final String serviceId, final Class serviceInterface) {
        if (logger.isDebugEnabled()) {
            logger.debug("getService(String, Class) - start");
        }

        Object returnObject = this.hivemindRegistry.getService(serviceId, serviceInterface);
        if (logger.isDebugEnabled()) {
            logger.debug("getService(String, Class) - end");
        }
        return returnObject;
    }

    /**
     * Convenience method to obtain a service with a single implementation from the registry.
     * Exactly one service point must implement the service.
     *
     * @param serviceInterface the class to which the service will be cast.
     * @return the service implementing the given interface.
     * @see #getService(String, Class)
     */
    public Object getService(final Class serviceInterface) {
        if (logger.isDebugEnabled()) {
            logger.debug("getService(Class) - start");
        }
        
        Object returnObject = this.hivemindRegistry.getService(serviceInterface);
        if (logger.isDebugEnabled()) {
            logger.debug("getService(Class) - end");
        }
        return returnObject;
    }
}
