package spring.java.integration.jms.config;

import java.util.Hashtable;
import javax.naming.Context;

/**
 *
 * @author rafael.guterres
 */
public class ServerConfig {
    
    private String initialContextFactory;
    private String providerUrl;
    private String securityPrincipal;
    private String securityCredentials;
    
    public String getInitialContextFactory() {
        return initialContextFactory;
    }

    public void setInitialContextFactory(String initialContextFactory) {
        this.initialContextFactory = initialContextFactory;
    }

    public String getProviderUrl() {
        return providerUrl;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }

    public String getSecurityPrincipal() {
        return securityPrincipal;
    }

    public void setSecurityPrincipal(String securityPrincipal) {
        this.securityPrincipal = securityPrincipal;
    }

    public String getSecurityCredentials() {
        return securityCredentials;
    }

    public void setSecurityCredentials(String securityCredentials) {
        this.securityCredentials = securityCredentials;
    }

}
