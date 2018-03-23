package com.honeyshop.dao;

import com.honeyshop.dao.generic.GenericDaoImpl;
import com.honeyshop.models.Customer;
import com.honeyshop.models.Order;
import com.honeyshop.models.Sale;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDao extends GenericDaoImpl<Order, Long>{


    public OrderDao() {
        super(Order.class);
    }

    public Order findByTrackingNumber(final String trackingNumber) {
        try {
            final TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o " +
                    "where o.trackingNumber = :trackingNumber", Order.class);
            query.setParameter("trackingNumber", trackingNumber);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
