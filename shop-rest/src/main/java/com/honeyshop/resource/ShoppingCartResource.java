package com.honeyshop.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeyshop.models.Cart;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.ShoppingCartService;
import org.json.JSONArray;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/shoppingCart")
public class ShoppingCartResource {

    private ShoppingCartService shoppingCartService;

    @Inject
    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @POST
    @Path("/update")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShoppingCart(@CookieParam("shoppingCart") NewCookie strCookie, Cart cart) {

            shoppingCartService.addProduct(cart.getProductId(), cart.getQuantity());
            List<ShoppingCart> list = shoppingCartService.findAll();
            List<Cart> carts = new ArrayList<>();
            list.forEach(shoppingCart -> carts.add(new Cart(shoppingCart.getProduct().getId(), shoppingCart.getQuantity())));
            JSONArray listObj = new JSONArray(carts);
            NewCookie cookie = new NewCookie(new Cookie("shoppingCart", listObj.toString()), "shopping-cart", 60 * 60 * 24, false);
            return Response.ok().cookie(cookie).build();
    }
}
