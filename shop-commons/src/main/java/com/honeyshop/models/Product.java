package com.honeyshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product extends AbstractEntity {

    @Column(name = "PRODUCT_NAME")
    private String productName;
    private Double price;
    private String description;
    private String image;
    private Double rating;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;

    public Product() {
    }


    public String getProductName() {
        return productName;
    }

    public Product(String productName, Double price, String description, String image, Double rating, List<Comment> comments) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.image = image;
        this.rating = rating;
        this.comments = comments;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
