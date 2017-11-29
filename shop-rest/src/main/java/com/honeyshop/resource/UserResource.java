package com.honeyshop.resource;

import com.honeyshop.models.User;
import com.honeyshop.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
public class UserResource {

    @Context
    private UriInfo uriInfo;

    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/login")
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        try {
            // Authenticate the user using the credentials provided
            userService.authenticate(login, password);
            // Issue a token for the user
            //String token = issueToken(login);
            // Return the token on the response
            //return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
            return Response.ok().build();//temporary
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @POST
    public Response create(User user) {
        userService.create(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        User user = userService.findOne(Long.parseLong(id));

        if (user == null)
            return Response.status(NOT_FOUND).build();
        return Response.ok(user).build();
    }

    @GET
    public Response findAllUsers() {
        List<User> allUsers = userService.findAll();

        if (allUsers == null)
            return Response.status(NOT_FOUND).build();

        return Response.ok(allUsers).build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") String id) {
        User user = userService.findOne(Long.parseLong(id));
        if (user == null) {
            return Response.status(NOT_FOUND).build();
        } else {
            userService.delete(user);
            return Response.noContent().build();
        }
    }

}
