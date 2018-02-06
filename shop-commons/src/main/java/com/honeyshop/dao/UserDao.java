package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.User;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao extends GenericDaoImpl<User, Long> {

    public UserDao() {
        super(User.class);
    }

    public User findByUsernameAndPassword(final String email, final String password) {
        try {
            final TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u " +
                    "where u.email = :email and u.password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
