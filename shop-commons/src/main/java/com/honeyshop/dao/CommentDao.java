package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Comment;

import javax.ejb.Stateless;

@Stateless
public class CommentDao extends GenericDaoImpl<Comment, Long> {

    public CommentDao() {
        super(Comment.class);
    }

}
