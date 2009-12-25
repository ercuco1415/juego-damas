package persistence.utils;
/*
 * Copyright 2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.ConnectionProviderFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;

/**
 * <p>A connection provider that uses an Apache commons DBCP connection pool.</p>
 *
 * <p>To use this connection provider set:<br>
 * <code>hibernate.connection.provider_class&nbsp;org.hibernate.connection.DBCPConnectionProvider</code></p>
 *
 * <pre>Supported Hibernate properties:
 *   hibernate.connection.driver_class
 *   hibernate.connection.url
 *   hibernate.connection.username
 *   hibernate.connection.password
 *   hibernate.connection.isolation
 *   hibernate.connection.autocommit
 *   hibernate.connection.pool_size
 *   hibernate.connection (JDBC driver properties)</pre>
 * <br>
 * All DBCP properties are also supported by using the hibernate.dbcp prefix.
 * A complete list can be found on the DBCP configuration page:
 * <a href="http://jakarta.apache.org/commons/dbcp/configuration.html">http://jakarta.apache.org/commons/dbcp/configuration.html</a>.
 * <br>
 * <pre>Example:
 *   hibernate.connection.provider_class org.hibernate.connection.DBCPConnectionProvider
 *   hibernate.connection.driver_class org.hsqldb.jdbcDriver
 *   hibernate.connection.username sa
 *   hibernate.connection.password
 *   hibernate.connection.url jdbc:hsqldb:test
 *   hibernate.connection.pool_size 20
 *   hibernate.dbcp.initialSize 10
 *   hibernate.dbcp.maxWait 3000
 *   hibernate.dbcp.validationQuery select 1 from dual</pre>
 *
 * <p>More information about configuring/using DBCP can be found on the
 * <a href="http://jakarta.apache.org/commons/dbcp/">DBCP website</a>.
 * There you will also find the DBCP wiki, mailing lists, issue tracking
 * and other support facilities</p>
 *
 * @see org.hibernate.connection.ConnectionProvider
 * @author Dirk Verbeeck
 */

/**
 * CustomDBCPConnectionProvider, para poder utilizar DBCP con Hibernate.
 * @author mgoldsman
 */
public class CustomDBCPConnectionProvider implements ConnectionProvider {
    //~ Static fields/initializers -----------------------------------------------------------------

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(CustomDBCPConnectionProvider.class);
    private static final String PREFIX = "hibernate.dbcp.";

    // Old Environment property for backward-compatibility (property removed in Hibernate3)
    private static final String DBCP_PS_MAXACTIVE = "hibernate.dbcp.ps.maxActive";

    // Property doesn't exists in Hibernate2
    private static final String AUTOCOMMIT = "hibernate.connection.autocommit";

    //~ Instance fields ----------------------------------------------------------------------------

    private BasicDataSource ds;

    //~ Methods ------------------------------------------------------------------------------------

    /**
     * Cerrar el provider
     * @throws HibernateException
     */
    public void close() throws HibernateException {
        if (logger.isDebugEnabled()) {
            logger.debug("Close CustomDBCPConnectionProvider");
        }

        logStatistics();
        try {
            if (getDs() != null) {
                getDs().close();
                setDs(null);
            } else {
                logger.warn("Cannot close DBCP pool (not initialized)");
            }
        } catch (Exception e) {
            throw new HibernateException("Could not close DBCP pool", e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Close CustomDBCPConnectionProvider complete");
        }
    }
    /**
     * Cerrar una conection
     * @param conn
     * @throws SQLException
     */
    public void closeConnection(Connection conn) throws SQLException {
        try {
            conn.close();
        } finally {
            logStatistics();
        }
    }
    /**
     * Configuracion de DBCP.
     *
     * @param props
     * @exception HibernateException
     */
    @SuppressWarnings("unchecked")
    public void configure(Properties props) throws HibernateException {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Configure CustomDBCPConnectionProvider");
            }

            // DBCP properties used to create the BasicDataSource
            Properties dbcpProperties = new Properties();

            // si estan seteadas estas propiedades cambio el Properties parametro 
            // por el que me retorna el EncryptManager
            

            // TODO reveer
            // Username / password
            String username = props.getProperty(Environment.USER);
            String password = props.getProperty(Environment.PASS);
            dbcpProperties.put("username", username);
            dbcpProperties.put("password", password);

            // DriverClass & url
            String jdbcDriverClass = props.getProperty(Environment.DRIVER);
            String jdbcUrl = props.getProperty(Environment.URL);
            dbcpProperties.put("driverClassName", jdbcDriverClass);
            dbcpProperties.put("url", jdbcUrl);

            // Isolation level
            String isolationLevel = props.getProperty(Environment.ISOLATION);
            if ((isolationLevel != null) && (isolationLevel.trim().length() > 0)) {
                dbcpProperties.put("defaultTransactionIsolation", isolationLevel);
            }

            // Turn off autocommit (unless autocommit property is set) 
            String autocommit = props.getProperty(AUTOCOMMIT);
            if ((autocommit != null) && (autocommit.trim().length() > 0)) {
                dbcpProperties.put("defaultAutoCommit", autocommit);
            } else {
                dbcpProperties.put("defaultAutoCommit", String.valueOf(Boolean.FALSE));
            }

            // Pool size
            String poolSize = props.getProperty(Environment.POOL_SIZE);
            if ((poolSize != null) &&
                    (poolSize.trim().length() > 0) &&
                    (Integer.parseInt(poolSize) > 0)) {
                dbcpProperties.put("maxActive", poolSize);
            }

            // Copy all "driver" properties into "connectionProperties"
            Properties driverProps = ConnectionProviderFactory.getConnectionProperties(props);
            if (driverProps.size() > 0) {
                StringBuffer connectionProperties = new StringBuffer();
                for (Iterator iter = driverProps.keySet().iterator(); iter.hasNext();) {
                    String key = (String) iter.next();
                    String value = driverProps.getProperty(key);
                    connectionProperties.append(key).append('=').append(value);
                    if (iter.hasNext()) {
                        connectionProperties.append(';');
                    }
                }
                dbcpProperties.put("connectionProperties", connectionProperties.toString());
            }

            // Copy all DBCP properties removing the prefix
            for (Iterator iter = props.keySet().iterator(); iter.hasNext();) {
                String key = String.valueOf(iter.next());
                if (key.startsWith(PREFIX)) {
                    String property = key.substring(PREFIX.length());
                    String value = props.getProperty(key);
                    dbcpProperties.put(property, value);
                }
            }

            // Backward-compatibility
            if (props.getProperty(DBCP_PS_MAXACTIVE) != null) {
                dbcpProperties.put("poolPreparedStatements", String.valueOf(Boolean.TRUE));
                dbcpProperties.put("maxOpenPreparedStatements", props.getProperty(DBCP_PS_MAXACTIVE));
            }

            // Some debug info
            if (logger.isDebugEnabled()) {
                logger.debug(
                    "Creating a DBCP BasicDataSource with the following DBCP factory properties:");
                StringWriter sw = new StringWriter();
                dbcpProperties.list(new PrintWriter(sw, true));
                logger.debug(sw.toString());
            }

            // Let the factory create the pool
            setDs((BasicDataSource) BasicDataSourceFactory.createDataSource(dbcpProperties));

            // The BasicDataSource has lazy initialization
            // borrowing a connection will start the DataSource
            // and make sure it is configured correctly.
            Connection conn = getDs().getConnection();
            conn.close();

            // Log pool statistics before continuing.
            logStatistics();
        } catch (Exception e) {
            String message = "Could not create a DBCP pool";
            logger.fatal(message, e);
            if (getDs() != null) {
                try {
                    getDs().close();
                } catch (Exception e2) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Cannot close DBCP pool (not initialized)");
                    }
                }
                setDs(null);
            }
            throw new HibernateException(message, e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Configure CustomDBCPConnectionProvider complete");
        }
    }
    /**
     * Obtener una conection
     * @return connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = getDs().getConnection();
        } finally {
            logStatistics();
        }
        return conn;
    }
    /**
     * Documentacion pendiente.
     *
     * @return (documentacion pendiente).
     */
    public boolean supportsAggressiveRelease() {
        //TODO Solo para cumplir con la interfaz org.hibernate.usertype.UserType
        return true;
    }
    /**
     * Loguea las estadisticas del pool
     */
    protected void logStatistics() {
        if (logger.isDebugEnabled()) {
            logger.debug("active: " + getDs().getNumActive() + " (max: " + getDs().getMaxActive() +
                ")   " + "idle: " + getDs().getNumIdle() + "(max: " + getDs().getMaxIdle() + ")");
        }
    }

    /**
     * getDs
     * @return ds
     */
    private BasicDataSource getDs() {
        return this.ds;
    }
    /**
     * setDs
     * @param ds
     */
    private void setDs(BasicDataSource ds) {
        this.ds = ds;
    }
}
