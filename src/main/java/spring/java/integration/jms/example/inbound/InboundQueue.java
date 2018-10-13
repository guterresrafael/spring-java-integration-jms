package spring.java.integration.jms.example.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import spring.java.integration.jms.core.JmsReceiver;
import org.springframework.stereotype.Service;
import spring.java.integration.jms.example.flow.IntegrationFlow;
import org.springframework.scheduling.annotation.Scheduled;
import spring.java.integration.jms.annotaion.JmsConfig;

/**
 *
 * @author rafael.guterres
 */
@Service
@EnableScheduling
@JmsConfig(connectionFactoryJndi = "jms.ConnectionFactory1",
           destinationJndi = "jms.Queue1")
public class InboundQueue extends JmsReceiver {

    @Autowired
    IntegrationFlow integrationFlow;
    
    @Override
    @Scheduled(fixedRate = 10000)
    public void receiveMessage() {
        super.receiveMessage();
    }

    @Override
    public void handleMessage(String message) throws Exception {
        integrationFlow.flow(message);
    }
}
