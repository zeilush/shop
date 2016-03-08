package zeilush.shop.jaxrs;

import zeilush.shop.order.boundary.OrderManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by AAA on 08.03.2016.
 */
@Path("/order")
public class Order {

    @Inject
    private OrderManager orderManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doOrder() {
        return orderManager.addOrder();
    }
}
