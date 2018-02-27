package com.honeyshop.services;

import com.honeyshop.dao.UserDao;
import com.honeyshop.models.User;
import com.honeyshop.services.generic.GenericServiceImpl;
import org.glassfish.jersey.internal.util.Base64;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.StringTokenizer;

@Stateless
public class UserService extends GenericServiceImpl<User> {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    @Inject
    private UserDao userDao;

    public UserService() {
    }

    public User authenticate(String email, String password) {
        User user = userDao.findByUsernameAndPassword(email, password);
        if (user != null) {
            return user;
        } else return null;
    }


    public User decodeUser(String encrypted) {
        final String encodedUserPassword = encrypted.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        return authenticate(username, password);
    }
}
