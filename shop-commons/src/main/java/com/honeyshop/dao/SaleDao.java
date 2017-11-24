package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Sale;

import javax.ejb.Stateless;

@Stateless
public class SaleDao extends GenericDaoImpl<Sale, Long> {

    public SaleDao() {
        super(Sale.class);
    }
}
