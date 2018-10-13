package spring.java.integration.jms.example.flow;

import spring.java.integration.jms.example.inbound.InboundQueue;
import spring.java.integration.jms.example.outbound.OutboundQueue;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rafael.guterres
 */
@Service
public class IntegrationFlow {

    private static final Logger LOG = Logger.getLogger(IntegrationFlow.class.getName());

    @Autowired
    InboundQueue inboundQueue;

    @Autowired
    OutboundQueue outboundQueue;

    public void flow(String message) throws Exception {
        System.out.println("IntegrationFlow.handle()");
        outboundQueue.sendMessage(message);
        System.out.println("IntegrationFlow.endHandle()");
    }
}
