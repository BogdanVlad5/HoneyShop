package com.honeyshop.services;

import com.honeyshop.dao.UserDao;
import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.User;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;

@Stateless
public class UserService extends GenericServiceImpl<User> {

    private UserDao userDao;

    public UserService() {
    }

    public UserService(UserDao userDao) {
        super(userDao);
        this.userDao = (UserDao) getGenericDao();
    }

    public void authenticate(String email, String password) throws Exception {
        User user = userDao.findByUsernameAndPassword(email, password);
        if (user == null)
            throw new SecurityException("Invalid user/password");
    }
}
