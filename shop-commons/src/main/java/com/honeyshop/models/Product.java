package com.honeyshop.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Product {

    private long productId;
    private long productListId;
    private String productName;
    private LocalDate dateSold;
    private String description;

    public Product(long productId, long productListId, String productName, LocalDate dateSold, String description) {
        this.productId = productId;
        this.productListId = productListId;
        this.productName = productName;
        this.dateSold = dateSold;
        this.description = description;
    }

    public Product() {

    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductListId() {
        return productListId;
    }

    public void setProductListId(long productListId) {
        this.productListId = productListId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (productListId != product.productListId) return false;
        if (productName != null ? !productName.equals(product.productName) : product.productName != null) return false;
        if (dateSold != null ? !dateSold.equals(product.dateSold) : product.dateSold != null) return false;
        return description != null ? description.equals(product.description) : product.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (productId ^ (productId >>> 32));
        result = 31 * result + (int) (productListId ^ (productListId >>> 32));
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (dateSold != null ? dateSold.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
