package com.honeyshop.resource;

import com.honeyshop.models.Cart;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.models.User;
import com.honeyshop.services.ShoppingCartService;
import com.honeyshop.services.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/shoppingCart")
public class ShoppingCartResource {
    private static final int MAX_AGE = 60 * 60 * 24;
    private static final String COOKIE_NAME = "shoppingCart";

    @Inject
    private ShoppingCartService shoppingCartService;

    @Inject
    private UserService userService;

    @Context
    HttpServletRequest request;

    @POST
    @Path("/update")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShoppingCart(Cart cart) {
        if (request.getSession() == null) {
            request.getSession(true);
            request.getSession().setAttribute("user", null);
            request.getSession().setAttribute("shoppingCart", null);
        }
        List<Cart> shoppingCart = (List<Cart>) request.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null || !shoppingCart.isEmpty()) {
            String userStr = (String) request.getSession().getAttribute("user");
            try{
                User user = userService.decodeUser(userStr);
                shoppingCartService.addProduct(cart.getProductId(), cart.getQuantity(), user);
                shoppingCart = shoppingCartService.addProductNoUser(shoppingCart, cart.getProductId(), cart.getQuantity());
            }catch(Exception e){
                shoppingCart = shoppingCartService.addProductNoUser(shoppingCart, cart.getProductId(), cart.getQuantity());
            }
        } else {
            //user logat - lucram cu db
            //trebuie verificat daca exista ceva in db pentru respectivul user pentru a updata sesiunea de shopping cart
            if (request.getSession().getAttribute("user") != null) {
                String userStr = (String) request.getSession().getAttribute("user");
                User user = userService.decodeUser(userStr);
                List<ShoppingCart> dbCartList = shoppingCartService.getShoppingCartForUser(user.getId());
                //userul nu are nimic in db
                if (dbCartList == null || dbCartList.isEmpty()) {
                    shoppingCartService.addProduct(cart.getProductId(), cart.getQuantity(), user);
                    shoppingCart.add(cart);
                }
                //userul avea ceva in db deja
                else {
                    shoppingCart = new ArrayList<>();
                    shoppingCartService.addProduct(cart.getProductId(), cart.getQuantity(), user);
                    dbCartList = shoppingCartService.getShoppingCartForUser(user.getId());
                    List<Cart> finalShoppingCart = shoppingCart;
                    dbCartList.forEach((ShoppingCart cartItem) -> finalShoppingCart
                            .add(new Cart(cartItem.getProduct().getId(), cartItem.getQuantity())));
                    shoppingCart = finalShoppingCart;
                }
            }
            //user nu ii logat - nu lucram cu db
            //adaugare obisnuita
            else {
                shoppingCart = shoppingCartService.addProductNoUser(shoppingCart, cart.getProductId(), cart.getQuantity());
            }
        }

        request.getSession().setAttribute("shoppingCart", shoppingCart);
        GenericEntity<List<Cart>> adapted = new GenericEntity<List<Cart>>(shoppingCart) {
        };
        return Response.ok(adapted).build();
    }

    //nu am verificat daca atunci cand userul ii logat si exista ceva in shopping cart sa updateze si sessionul cu valoarea aia

    @GET
    @Path("/test")
    @PermitAll
    public Response addUser() {
        User user = userService.findOne(1L);
        if (request.getSession() == null) {
            request.getSession(true);
        }
        request.getSession().setAttribute("user", user);
        return Response.ok().build();
    }
}
