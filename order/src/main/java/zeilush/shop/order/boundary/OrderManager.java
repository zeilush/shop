package zeilush.shop.order.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zeilush.shop.order.OrderRepository;
import zeilush.shop.order.entity.Order;
import zeilush.shop.order.entity.OrderItem;

import javax.ejb.*;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by AAA on 08.03.2016.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    private static final Logger logger = LoggerFactory.getLogger(OrderManager.class);
    @Inject
    OrderRepository orderRepository;
    @Inject
    private EntityManager em;
    @Inject
    private String configStr;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public OrderItem createItem(OrderItem item) {
        System.out.print("configStr; " + configStr);

        em.persist(item);

        return item;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Order createOrder(Order order) {
        em.persist(order);

        return order;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Order> findOrderByName(String name) {
        logger.info("configStr {} " + configStr, configStr);
        return orderRepository.findByNameStartsWith(name);
    }
}
