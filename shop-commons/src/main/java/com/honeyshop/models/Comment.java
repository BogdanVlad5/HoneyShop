package com.honeyshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
public class Comment extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
    private Product product;
    private String name;
    private String email;
    private String review;
    private Integer likes;

    public Comment() {
    }

    public Comment(Product product, String name, String email, String review, Integer likes) {
        this.product = product;
        this.name = name;
        this.email = email;
        this.review = review;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
