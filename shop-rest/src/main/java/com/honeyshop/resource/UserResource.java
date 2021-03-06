package com.honeyshop.resource;

import com.honeyshop.models.User;
import com.honeyshop.resource.commons.UserLoginRequest;
import com.honeyshop.services.UserService;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/users")
public class UserResource {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final int MAX_AGE = 60 * 60 * 24;
    private static final String EXPIRE = ";Max-Age=0";

    @Context
    private UriInfo uriInfo;

    @Context
    private HttpServletRequest request;

    @Inject
    private UserService userService;

    @PermitAll
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(UserLoginRequest userLoginRequest) {
        try {
            // Authenticate the user using the credentials provided
            User user = userService.authenticate(userLoginRequest.getUsername(), userLoginRequest.getPassword());

            String authString = userLoginRequest.getUsername() + ":" + userLoginRequest.getPassword();
            byte[] authEncBytes = Base64.encode(authString.getBytes());
            String usernameAndPassword = new String(AUTHENTICATION_SCHEME + " " + new String(authEncBytes));

            GenericEntity<User> adapted = new GenericEntity<User>(user) {
            };
            request.getSession(true);
            request.getSession().setAttribute("user", usernameAndPassword);
            NewCookie authCookie = new NewCookie(AUTHORIZATION_PROPERTY+"", usernameAndPassword+"",
                    "/", "", "comment", MAX_AGE, false);
            return Response.ok(adapted).cookie(authCookie).build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @PermitAll
    @GET
    @Path("/logout")
    public Response logout() {
        request.getSession(false);
        NewCookie cookie = NewCookie.valueOf(AUTHORIZATION_PROPERTY + EXPIRE);
        return Response.ok().cookie(cookie).build();
    }

    @POST
    public Response create(User user) {
        userService.create(user);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") String id) {
        User user = userService.findOne(Long.parseLong(id));

        if (user == null)
            return Response.status(NOT_FOUND).build();
        GenericEntity<User> adapted = new GenericEntity<User>(user) {
        };
        return Response.ok(adapted).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllUsers() {
        List<User> allUsers = userService.findAll();

        if (allUsers == null)
            return Response.status(NOT_FOUND).build();

        GenericEntity<List<User>> users = new GenericEntity<List<User>>(allUsers) {
        };
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
