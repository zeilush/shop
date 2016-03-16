package zeilush.shop.jaxrs;

import zeilush.shop.order.boundary.OrderManager;
import zeilush.shop.order.entity.Order;
import zeilush.shop.order.entity.OrderItem;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by AAA on 08.03.2016.
 */
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Inject
    private OrderManager orderManager;

    @POST
    public Order createOrder(Order order) {
        return orderManager.createOrder(order);
    }

    @GET
    @Path("/search")
    public List<Order> findOrderByName(@QueryParam("name") String name) {
        return orderManager.findOrderByName(name);
    }

    @Path("/item")
    @POST
    public OrderItem createItem(OrderItem item) {
        System.out.print(item.getName());
        return orderManager.createItem(item);
    }
}
