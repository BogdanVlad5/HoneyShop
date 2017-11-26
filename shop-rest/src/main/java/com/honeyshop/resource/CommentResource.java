package com.honeyshop.resource;

import com.honeyshop.models.Comment;
import com.honeyshop.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comments")
public class CommentResource {

    private CommentService commentService;

    @Inject
    public CommentResource(CommentService commentService){
        this.commentService = commentService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Long id)
    {
        Comment comment = commentService.findOne(id);
        GenericEntity<Comment> adapted = new GenericEntity<Comment>(comment) {
        };
        return Response.ok(adapted).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments()
    {
        List<Comment> comments = commentService.findAll();
        GenericEntity<List<Comment>> adapted = new GenericEntity<List<Comment>>(comments) {
        };
        return Response.ok(adapted).build();
    }
}
