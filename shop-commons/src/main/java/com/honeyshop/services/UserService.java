package com.honeyshop.services;

import com.honeyshop.dao.UserDao;
import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.User;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService extends GenericServiceImpl<User> {

    private UserDao userDao;

    public UserService() {
    }

    @Inject
    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao = (UserDao) getGenericDao();
    }

    public User authenticate(String email, String password) throws Exception {
        User user = userDao.findByUsernameAndPassword(email, password);
        if (user != null){
            return user;
        }else return null;
    }
}
