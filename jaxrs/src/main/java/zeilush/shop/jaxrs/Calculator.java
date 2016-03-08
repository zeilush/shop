package zeilush.shop.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by AAA on 07.03.2016.
 */
@Path("/calc")
public class Calculator {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {

        return "hello: wildfly swarm + gradle + java";
    }
}
