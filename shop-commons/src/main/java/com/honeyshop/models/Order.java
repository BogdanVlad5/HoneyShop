package com.honeyshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity {

    @OneToOne
    private Customer customer;
    private String trackingNumber;
    private OrderStatus orderStatus;
    @OneToMany
    @JoinTable(name = "ORDER_SALES", joinColumns = {
            @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "SALE_ID", referencedColumnName = "ID")
    })
    private List<Sale> sales;

    public Order() {
    }

    public Order(Customer customer, String trackingNumber, OrderStatus orderStatus, List<Sale> list) {
        this.customer = customer;
        this.trackingNumber = trackingNumber;
        this.orderStatus = orderStatus;
        this.sales = list;
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

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
