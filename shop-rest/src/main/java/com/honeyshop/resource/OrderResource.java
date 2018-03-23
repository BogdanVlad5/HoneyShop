package com.honeyshop.resource;

import com.honeyshop.models.OrderStatus;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.OrderService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
public class OrderResource {

    @Inject
    private OrderService orderService;


    @PermitAll
    @GET
    @Path("/{orderId}/status")
    public Response getOrderStatus(@PathParam("orderId") long orderId) {
        OrderStatus orderStatus = orderService.getOrderStatus(orderId);
        return Response.ok(orderStatus).build();
    }

    @PermitAll
    @POST
    public Response createOrder(@QueryParam("customerId") long customerId, List<ShoppingCart> shoppingCartList) {
        orderService.create(shoppingCartList, customerId);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{id}")
    public Response getOrder(@QueryParam("id") long id){
        return Response.ok(orderService.findOne(id)).build();
    }

}
