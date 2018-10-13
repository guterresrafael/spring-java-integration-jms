package spring.java.integration.jms;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;

/**
 *
 * @author rafael.guterres
 */
@SpringBootApplication
@PropertySource("classpath:META-INF/java/integration/application.properties")
//@EnableJms
public class SpringJavaIntegrationJmsApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringJavaIntegrationJmsApplication.class, args);
        System.out.println("JavaIntegrationJmsApplication RUNNING...");
    }
}
