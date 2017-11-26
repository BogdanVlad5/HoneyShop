package com.honeyshop.services;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Comment;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;

@Stateless
public class CommentService extends GenericServiceImpl<Comment> {

    public CommentService() {
    }

    public CommentService(GenericDaoImpl genericDao) {
        super(genericDao);
    }
}
