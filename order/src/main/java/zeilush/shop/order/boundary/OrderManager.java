package zeilush.shop.order.boundary;

import zeilush.shop.order.control.Orderservice;

import javax.ejb.Stateless;

/**
 * Created by AAA on 08.03.2016.
 */
@Stateless
public class OrderManager {

    private Orderservice orderservice;

    public String addOrder() {
        return "hallo";
    }

}
