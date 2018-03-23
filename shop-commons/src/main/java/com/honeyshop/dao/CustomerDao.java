package com.honeyshop.dao;


import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Customer;

public class CustomerDao extends GenericDaoImpl<Customer, Long> {

    public CustomerDao() {
        super(Customer.class);
    }
}
