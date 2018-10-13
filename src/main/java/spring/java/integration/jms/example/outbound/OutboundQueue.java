package spring.java.integration.jms.example.outbound;

import spring.java.integration.jms.core.JmsSender;
import org.springframework.stereotype.Service;
import spring.java.integration.jms.annotaion.JmsConfig;

/**
 *
 * @author rafael.guterres
 */
@Service
@JmsConfig(connectionFactoryJndi = "jms.ConnectionFactory1",
           destinationJndi = "jms.Queue2")
public class OutboundQueue extends JmsSender {

}