package com.honeyshop.services;

import com.honeyshop.dao.ProductDao;
import com.honeyshop.dao.ShoppingCartDao;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Stateless
public class ShoppingCartService extends GenericServiceImpl<ShoppingCart>{

    private ProductDao productDao;
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartService(){}

    @Inject
    public ShoppingCartService(ProductDao productDao, ShoppingCartDao shoppingCartDao) {
        super(shoppingCartDao);
        this.shoppingCartDao = shoppingCartDao;
        this.productDao = productDao;
    }

    public void addProduct(Long productId, int quantity) {
        List<ShoppingCart> cart = shoppingCartDao.findAll();
        AtomicBoolean exists = new AtomicBoolean(false);
        cart.forEach((ShoppingCart cartItem) -> {
            if(cartItem.getProduct().getId() == productId){
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                shoppingCartDao.update(cartItem);
                exists.set(true);
            }
        });
        if (!exists.get()){
            shoppingCartDao.create(new ShoppingCart(productDao.findOne(productId),quantity));
        }
    }
}
