package spring.java.integration.jms.core;

import java.util.HashMap;
import java.util.Map;
import javax.jms.ConnectionFactory;
import javax.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 *
 * @author rafael.guterres
 */
//@Configuration
public abstract class JmsConfig {
    
    //@Bean
    public JmsTemplate jmsTemplate() throws Exception {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }

    //@Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    //@Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    //@Bean
    public MarshallingMessageConverter createMarshallingMessageConverter(final Jaxb2Marshaller jaxb2Marshaller) {
        return new MarshallingMessageConverter(jaxb2Marshaller);
    }

    //@Bean
    public Jaxb2Marshaller createJaxb2Marshaller(@Value("${context.path}") final String contextPath,
                                            @Value("${schema.location}") final Resource schemaResource) {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath(contextPath);
        jaxb2Marshaller.setSchema(schemaResource);        
        Map<String, Object> properties = new HashMap<>();
        properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxb2Marshaller.setMarshallerProperties(properties);
        return jaxb2Marshaller;
    }
}
