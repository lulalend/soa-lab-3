package ru.itmo.soa.mainservice.config;

import ru.itmo.soa.ejb.services.BandServiceIEJB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class JndiConfig {
//    private static final String WILDFLY_HOST = "localhost";
    private static final String WILDFLY_HOST = "soa-lab-3-main-service-ejb-1";
    private static final int EJB_PORT = 8080;
    private static final String EJB_JNDI_NAME = "ejb:/ejb-module-0.0.1-SNAPSHOT/BandServiceEJB!ru.itmo.soa.ejb.services.BandServiceIEJB";
    private static final Context context;

    static {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
        jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "user");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "user");
        jndiProperties.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "http-remoting://" + WILDFLY_HOST + ":" + EJB_PORT));

        try {
            context = new InitialContext(jndiProperties);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public static BandServiceIEJB getBandServiceEJB() {
        try {
            return (BandServiceIEJB) context.lookup(EJB_JNDI_NAME);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
