package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductDao extends GenericDaoImpl<Product, Long> {
    public ProductDao() {
        super(Product.class);
    }
}
