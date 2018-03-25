package com.honeyshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends AbstractEntity {

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User user;
    private String address;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
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
