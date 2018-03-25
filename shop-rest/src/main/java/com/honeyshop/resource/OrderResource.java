package com.honeyshop.resource;

import com.honeyshop.models.Order;
import com.honeyshop.models.OrderStatus;
import com.honeyshop.models.ShoppingCart;
import com.honeyshop.services.OrderService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") long id){
        Order order = orderService.findOne(id);
        GenericEntity<Order> adapted = new GenericEntity<Order>(order) {
        };
        return Response.ok(adapted).build();
    }

}
