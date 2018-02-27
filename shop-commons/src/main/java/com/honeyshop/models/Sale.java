package com.honeyshop.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "SALES")
public class Sale extends AbstractEntity {

    private Integer quantity;
    private Long totalPrice;
    private Product product;

    private Order order;

    public Sale() {
    }

    public Sale(Integer quantity, Long totalPrice, Product product, Order order) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.product = product;
        this.order = order;
    }

    public Sale(Integer quantity, Long totalPrice) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
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
