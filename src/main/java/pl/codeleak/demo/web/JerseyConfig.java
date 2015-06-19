package pl.codeleak.demo.web;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Profile("web")
@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(CustomerController.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}

