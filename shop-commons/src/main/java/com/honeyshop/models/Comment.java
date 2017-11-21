package com.honeyshop.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COMMENTS")
public class Comment extends AbstractEntity {

    private String name;
    private String email;
    private String review;
    private Integer likes;

    public Comment() {
    }

    public Comment(String name, String email, String review, Integer likes) {
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
}
