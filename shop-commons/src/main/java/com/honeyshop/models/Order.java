package com.honeyshop.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity{

    private Customer customer;
    private String trackingNumber;
    private OrderStatus orderStatus;
    private List<Product> productList;

    public Order() {
    }

    public Order(Customer customer, String trackingNumber, OrderStatus orderStatus, List<Product> list) {
        this.customer = customer;
        this.trackingNumber = trackingNumber;
        this.orderStatus = orderStatus;
        this.productList = list;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
