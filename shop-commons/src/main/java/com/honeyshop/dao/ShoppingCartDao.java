package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.ShoppingCart;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ShoppingCartDao extends GenericDaoImpl<ShoppingCart, Long> {

    public ShoppingCartDao() {
        super(ShoppingCart.class);
    }

    public List<ShoppingCart> getShoppingCartForUser(Long userId){
        return entityManager
                .createQuery("SELECT s FROM ShoppingCart s WHERE s.user.id ="+userId)
                .getResultList();
    }
}
