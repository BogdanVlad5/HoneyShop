package com.honeyshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "SALES")
public class Sale extends AbstractEntity {

    private Integer quantity;
    private Long totalPrice;
    @OneToOne
    @JsonManagedReference
    private Product product;
    @ManyToOne(optional = false)
    @JoinColumn(name = "ORDER_ID")
    @JsonBackReference
    private Order order;

    public Sale() {
    }

    public Sale(Integer quantity, Long totalPrice, Product product, Order order) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
