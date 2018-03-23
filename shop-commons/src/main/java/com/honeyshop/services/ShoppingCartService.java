package com.honeyshop.services;

import com.honeyshop.dao.ProductDao;
import com.honeyshop.dao.ShoppingCartDao;
import com.honeyshop.models.Cart;
import com.honeyshop.models.Product;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.models.User;
import com.honeyshop.services.generic.GenericServiceImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Stateless
public class ShoppingCartService extends GenericServiceImpl<ShoppingCart> {

    @Inject
    private ProductDao productDao;
    @Inject
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartService() {
    }

    public void addProduct(Long productId, int quantity, User user) {
        List<ShoppingCart> cart = shoppingCartDao.findAll();
        AtomicBoolean exists = new AtomicBoolean(false);
        cart.forEach((ShoppingCart cartItem) -> {
            if (cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                shoppingCartDao.update(cartItem);
                exists.set(true);
            }
        });
        if (exists.get() == false) {
            Product product = productDao.findOne(productId);
            if (product == null) {
                throw new EntityNotFoundException();
            } else {
                shoppingCartDao.create(new ShoppingCart(product, quantity, user));
            }
        }
    }

    public List<ShoppingCart> getShoppingCartForUser(Long userId) {
        return shoppingCartDao.getShoppingCartForUser(userId);
    }

    public List<Cart> addProductNoUser(List<Cart> shoppingCart, Long productId, int quantity) {
        AtomicReference<Boolean> present = new AtomicReference<>(false);
        if (shoppingCart == null || shoppingCart.isEmpty()) {
            shoppingCart = new ArrayList<>();
            shoppingCart.add(new Cart(productId, quantity));
            return shoppingCart;
        } else {
            shoppingCart.forEach(actualCart -> {
                if (productId == actualCart.getProductId()) {
                    actualCart.setQuantity(actualCart.getQuantity() + quantity);
                    present.set(true);
                }
            });
            if (present.get() == false){
                shoppingCart.add(new Cart(productId, quantity));
            }
            return shoppingCart;
        }
    }
}
