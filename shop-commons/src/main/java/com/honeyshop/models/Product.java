package com.honeyshop.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product extends AbstractEntity {

    private String productName;
    private Double price;
    private String description;
    @OneToOne
    @JoinColumn(name = "PRODUCT_DETAIL_ID")
    private ProductDetail productDetail;
    @OneToOne
    @JoinColumn(name = "SALE_ID")
    private Sale sale;

    public Product() {
    }

    public Product(String productName, Double price, String description, ProductDetail productDetail, Sale sale) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.productDetail = productDetail;
        this.sale = sale;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
