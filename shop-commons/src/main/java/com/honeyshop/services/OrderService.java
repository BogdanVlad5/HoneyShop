package com.honeyshop.services;

import com.honeyshop.dao.CustomerDao;
import com.honeyshop.dao.OrderDao;
import com.honeyshop.dao.SaleDao;
import com.honeyshop.models.Order;
import com.honeyshop.models.OrderStatus;
import com.honeyshop.models.Sale;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class OrderService extends GenericServiceImpl<Order>{

    private OrderDao orderDao;

    @Inject
    private SaleDao saleDao;

    @Inject
    private CustomerDao customerDao;

    public OrderService() {
    }

    public OrderStatus getOrderStatus(long orderId) {
        Order order = orderDao.findOne(orderId);
        return order.getOrderStatus();
    }

    public void create(List<ShoppingCart> shoppingCartList, Long customerId)  {
        Order order = new Order();
        order.setCustomer(customerDao.findOne(customerId));
        order.setOrderStatus(OrderStatus.PROCESSED);
        order.setSales(Collections.emptyList());
        order.setTrackingNumber("134436573");
        orderDao.create(order);
        shoppingCartList.forEach(shoppingCart -> {
            Sale sale = new Sale();
            sale.setProduct(shoppingCart.getProduct());
            sale.setQuantity(shoppingCart.getQuantity());
            sale.setTotalPrice(100L);
            saleDao.create(sale);
            Order newOrder = orderDao.findByTrackingNumber(order.getTrackingNumber());
            List<Sale> saleList = newOrder.getSales();
            saleList.add(sale);
            newOrder.setSales(saleList);
            orderDao.update(newOrder);
        });
    }
}
