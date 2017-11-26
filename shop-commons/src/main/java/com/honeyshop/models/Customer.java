package com.honeyshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends AbstractEntity{

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String address;
    @OneToMany
    @JoinTable(name = "CUSTOMERS_ORDER_HISTORY", joinColumns = {
            @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "ORDER_HISTORY_ID", referencedColumnName = "ID")
    })
    private List<OrderHistory> orders;

    public Customer() {
    }

    public Customer(String firstName, String lastName, User user, String address, List<OrderHistory> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.address = address;
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderHistory> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderHistory> orders) {
        this.orders = orders;
    }
}
