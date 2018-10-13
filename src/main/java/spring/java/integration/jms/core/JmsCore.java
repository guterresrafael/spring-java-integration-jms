package spring.java.integration.jms.core;

import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;
import spring.java.integration.jms.annotaion.JmsConfig;

/**
 *
 * @author rafael.guterres
 */
public abstract class JmsCore {

    private static final Logger LOG = Logger.getLogger(JmsCore.class.getName());

    @Resource(name = "serverContext")
    Context context;

    ConnectionFactory connectionFactory;
    Connection connection;
    Session session;
    Destination destination;

    String connectionFactoryJndi;
    String destinationJndi;
    String messageSelector;
    Boolean enableTransaction;
    Boolean enableListener;

    public JmsCore() {
        for (Annotation annotation : this.getClass().getAnnotations()) {
            if (annotation instanceof JmsConfig) {
                JmsConfig config = (JmsConfig) annotation;
                connectionFactoryJndi = config.connectionFactoryJndi();
                destinationJndi = config.destinationJndi();
                messageSelector = config.messageSelector();
                enableTransaction = config.enableTransaction();
                enableListener = config.enableListener();
            }
        }
    }

    void init() {
        try {
            connectionFactory = (ConnectionFactory) context.lookup(connectionFactoryJndi);
            connection = connectionFactory.createConnection();
            if (enableTransaction) {
                session = connection.createSession(true, Session.SESSION_TRANSACTED);
            } else {
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            }
            destination = (Destination) context.lookup(destinationJndi);
        } catch (NamingException | JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    void startConnection() {
        if (connection != null) {
            try {
                connection.start();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    void stopConnection() {
        if (connection != null) {
            try {
                connection.stop();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    void commitSession() {
        if (session != null) {
            try {
                session.commit();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    void rollbackSession() {
        if (session != null) {
            try {
                session.rollback();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    void closeConnection() {
        if (session != null) {
            try {
                session.close();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException ex) {
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

}
