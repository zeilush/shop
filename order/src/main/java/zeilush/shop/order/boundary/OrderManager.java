package zeilush.shop.order.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeilush.shop.common.interceptor.Monitored;
import zeilush.shop.order.OrderRepository;
import zeilush.shop.order.entity.Order;
import zeilush.shop.order.entity.OrderItem;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by AAA on 08.03.2016.
 */
@Monitored
@Stateless
public class OrderManager {

    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);

    @Inject
    OrderRepository orderRepository;

    @Inject
    private EntityManager em;


    public OrderItem createItem(@Valid OrderItem item) {
        em.persist(item);

        return item;
    }

    public Order createOrder(Order order) {
        em.persist(order);

        return order;
    }

    public List<Order> findOrderByName(String name) {
        return orderRepository.findByNameStartsWith(name);
    }
}
