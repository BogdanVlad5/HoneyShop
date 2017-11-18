package com.honeyshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productDetails")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne
    private Product product;
    private String image;
    private Double rating;
    @OneToMany
    private List<Comment> comments;

    public ProductDetail() {
    }

    public ProductDetail(Product product, String image, Double rating, List<Comment> comments) {
        this.product = product;
        this.image = image;
        this.rating = rating;
        this.comments = comments;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
