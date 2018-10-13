package spring.java.integration.jms.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

/**
 *
 * @author rafael.guterres
 */
public abstract class JmsSender extends JmsCore {

    private static final Logger LOG = Logger.getLogger(JmsSender.class.getName());

    private MessageProducer messageProducer;

    public void sendMessage(String message) throws JMSException {
        super.init();
        try {
            messageProducer = session.createProducer(destination);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            if (enableTransaction) {
                session.commit();
            }
        } catch (JMSException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            rollbackSession();
            throw new JMSException(ex.getMessage());
        } finally {
            if (messageProducer != null) {
                try {
                    messageProducer.close();
                } catch (JMSException ex) {
                    LOG.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
            closeConnection();
        }
    }
}
