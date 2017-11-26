package com.honeyshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderHistory extends AbstractEntity{

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ORDER_HISTORY_ORDERS", joinColumns = {
            @JoinColumn(name = "ORDER_HISTORY_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    })
    private List<Order> orders;
    @ManyToOne
    private Customer customer;

    public OrderHistory() {
    }

    public OrderHistory(List<Order> orders, Customer customer) {
        this.orders = orders;
        this.customer = customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
