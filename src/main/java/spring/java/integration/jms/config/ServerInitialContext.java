package spring.java.integration.jms.config;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rafael.guterres
 */
public abstract class ServerInitialContext {

    public InitialContext initContext(ServerConfig serverConfig) throws NamingException {
        Hashtable<String, String> environment = new Hashtable<>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY, serverConfig.getInitialContextFactory());
        environment.put(Context.PROVIDER_URL, serverConfig.getProviderUrl());
        environment.put(Context.SECURITY_PRINCIPAL, serverConfig.getSecurityPrincipal());
        environment.put(Context.SECURITY_CREDENTIALS, serverConfig.getSecurityCredentials());
        return new InitialContext(environment);
    }
}
