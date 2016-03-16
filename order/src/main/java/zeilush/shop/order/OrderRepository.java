package zeilush.shop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import zeilush.shop.order.entity.Order;

import java.util.List;

/**
 * Created by AAA on 16.03.2016.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByNameStartsWith(String name);
}
