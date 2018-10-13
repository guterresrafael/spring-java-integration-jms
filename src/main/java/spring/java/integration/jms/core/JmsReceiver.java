package spring.java.integration.jms.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.naming.NamingException;

/**
 *
 * @author rafael.guterres
 */
public abstract class JmsReceiver extends JmsCore {

    private static final Logger LOG = Logger.getLogger(JmsReceiver.class.getName());

    private MessageConsumer messageConsumer;

    @PostConstruct
    void initListener() {
        try {
            if (!enableListener) {
                return;
            }
            super.init();
            destination = (Destination) context.lookup(destinationJndi);
            if (messageSelector != null && !messageSelector.isEmpty()) {
                messageConsumer = session.createConsumer(destination, messageSelector);
            } else {
                messageConsumer = session.createConsumer(destination);
            }
            super.startConnection();
        } catch (JMSException | NamingException ex) {
            Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        try {
            if (!enableListener) {
                return;
            }
            Message message = messageConsumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                this.handleMessage(textMessage.getText());
            } else {
                this.handleMessage(message.toString());
            }
            super.commitSession();
        } catch (Exception ex) {
            Logger.getLogger(JmsReceiver.class.getName()).log(Level.SEVERE, null, ex);
            super.rollbackSession();
        }
    }

    public abstract void handleMessage(String message) throws Exception;
}
