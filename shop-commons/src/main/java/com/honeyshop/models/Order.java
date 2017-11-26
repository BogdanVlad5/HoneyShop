package com.honeyshop.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity{

    private Customer customer;
    private String trackingNumber;
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(Customer customer, String trackingNumber, OrderStatus orderStatus) {
        this.customer = customer;
        this.trackingNumber = trackingNumber;
        this.orderStatus = orderStatus;
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
}
