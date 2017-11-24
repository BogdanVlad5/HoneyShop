package com.honeyshop.services;

import com.honeyshop.dao.CommentDao;
import com.honeyshop.models.Comment;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CommentService {

    @Inject
    private CommentDao commentDao;

    public Comment findOne(Long id) {
        return commentDao.findOne(id, Comment.class);
    }
}
