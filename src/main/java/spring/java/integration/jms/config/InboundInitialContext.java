package spring.java.integration.jms.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author rafael.guterres
 */
@Configuration
public class InboundInitialContext extends ServerInitialContext {

    @Value("${inbound.java.naming.factory.initial}")
    private String WEBLOGIC_INITIAL_CONTEXT_FACTORY;

    @Value("${inbound.java.naming.provider.url}")
    private String WEBLOGIC_PROVIDER_URL;

    @Value("${inbound.java.naming.security.principal}")
    private String WEBLOGIC_SECURITY_PRINCIPAL;

    @Value("${inbound.java.naming.security.credentials}")
    private String WEBLOGIC_SECURITY_CREDENTIALS;

    @Bean(name = "serverContext")
    public InitialContext serverContext() throws NamingException {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setInitialContextFactory(WEBLOGIC_INITIAL_CONTEXT_FACTORY);
        serverConfig.setProviderUrl(WEBLOGIC_PROVIDER_URL);
        serverConfig.setSecurityPrincipal(WEBLOGIC_SECURITY_PRINCIPAL);
        serverConfig.setSecurityCredentials(WEBLOGIC_SECURITY_CREDENTIALS);
        return super.initContext(serverConfig);
    }

}
