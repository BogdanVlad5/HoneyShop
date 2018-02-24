package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.ShoppingCart;

import javax.ejb.Stateless;

@Stateless
public class ShoppingCartDao extends GenericDaoImpl<ShoppingCart, Long> {
    public ShoppingCartDao() {
        super(ShoppingCart.class);
    }
}
