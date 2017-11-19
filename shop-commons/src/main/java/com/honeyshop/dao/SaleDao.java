package com.honeyshop.dao;

import com.honeyshop.models.Sale;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SaleDao {

    @PersistenceContext(unitName="shop-PU")
    EntityManager entityManager;

    public Sale findOne(Long id) {
        return entityManager.find(Sale.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void insert(Sale sale) {
        entityManager.persist(sale);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void editSale(Sale sale){
        entityManager.merge(sale);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteSale(long id){
        Sale sale = entityManager.find(Sale.class,id);
        if(sale !=null){
            entityManager.remove(sale);
        }
    }
}
