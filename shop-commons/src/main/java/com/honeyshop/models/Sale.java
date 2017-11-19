package com.honeyshop.models;

import org.apache.derby.client.am.DateTime;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DateTime dateSold;
    private int amountSold;
    private boolean available;

    @OneToOne
    private Product product;

    public Sale() {
    }

    public Sale(DateTime dateSold, int amountSold, Product product, boolean available) {
        this.dateSold = dateSold;
        this.amountSold = amountSold;
        this.product = product;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getDateSold() {
        return dateSold;
    }

    public void setDateSold(DateTime dateSold) {
        this.dateSold = dateSold;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
