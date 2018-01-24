package com.honeyshop.resource;

import com.honeyshop.models.User;
import com.honeyshop.services.UserService;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
public class UserResource {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpHeaders httpHeaders;

    private UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @POST
    @Path("/login")
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {
        try {
            // Authenticate the user using the credentials provided
            User user = userService.authenticate(login, password);
            httpHeaders.getRequestHeader(AUTHORIZATION_PROPERTY).clear();

            String authString = login + ":" + password;
            byte[] authEncBytes = Base64.encode(authString.getBytes());
            String usernameAndPassword = new String(AUTHENTICATION_SCHEME + " " + new String(authEncBytes));

            httpHeaders.getRequestHeader(AUTHORIZATION_PROPERTY).add(usernameAndPassword);
            GenericEntity<User> adapted = new GenericEntity<User>(user) {
            };
            return Response.ok(user).build();
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
        GenericEntity<User> adapted = new GenericEntity<User>(user) {
        };
        return Response.ok(adapted).build();
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
