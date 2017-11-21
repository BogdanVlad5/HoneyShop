package com.honeyshop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetail extends AbstractEntity {

    private String image;
    private Double rating;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PRODUCT_DETAILS_COMMENTS", joinColumns = {
            @JoinColumn(name = "PRODUCT_DETAILS_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
            @JoinColumn(name = "COMMENT_ID", referencedColumnName = "ID")
    })
    private List<Comment> comments;

    public ProductDetail() {
    }

    public ProductDetail(String image, Double rating, List<Comment> comments) {
        this.image = image;
        this.rating = rating;
        this.comments = comments;
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
