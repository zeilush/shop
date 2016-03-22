package zeilush.shop.order.entity;

import zeilush.shop.common.validator.name.Name;

import javax.persistence.*;

/**
 * Created by AAA on 13.03.2016.
 */
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Name
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
