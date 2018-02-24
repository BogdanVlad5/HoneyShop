package com.honeyshop.models;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;
    private int quantity;

    public ShoppingCart() {
    }

    public ShoppingCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
