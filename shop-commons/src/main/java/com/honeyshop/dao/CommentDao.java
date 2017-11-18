package com.honeyshop.dao;

import com.honeyshop.models.Comment;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CommentDao {

    @PersistenceContext(unitName="shop-PU")
    EntityManager entityManager;

    public Comment findOne(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(Comment comment) {
        entityManager.persist(comment);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editComment(Comment comment){
        entityManager.merge(comment);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteComment(int id){
        Comment comment = entityManager.find(Comment.class,id);
        if(comment !=null){
            entityManager.remove(comment);
        }
    }
}
