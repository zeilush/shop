package zeilush.shop.jaxrs;

import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeilush.shop.common.config.ApplicationConfig;
import zeilush.shop.common.interceptor.Monitored;
import zeilush.shop.order.boundary.OrderManager;
import zeilush.shop.order.entity.Order;
import zeilush.shop.order.entity.OrderItem;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by AAA on 08.03.2016.
 */
@Monitored
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private static final Logger logger = LoggerFactory.getLogger(OrderResource.class);

    @Inject
    @ConfigProperty(name = "app.version")
    private String appVersion;

    @Inject
    private ApplicationConfig appConfig;

    @Inject
    private OrderManager orderManager;

    @POST
    public Order createOrder(Order order) {
        return orderManager.createOrder(order);
    }

    @GET
    @Path("/search")
    public List<Order> findOrderByName(@QueryParam("name") String name) {
        logger.info("Appversion: {}", appConfig.appVersion());
        return orderManager.findOrderByName(name);
    }

    @Path("/item")
    @POST
    public OrderItem createItem(@Valid OrderItem item) {
        return orderManager.createItem(item);
    }
}
