package com.honeyshop.resource;

import com.honeyshop.models.Comment;
import com.honeyshop.services.CommentService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/comments")
public class CommentResource {

    private CommentService commentService;

    @Inject
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommentById(@PathParam("id") Long id) {
        Comment comment = commentService.findOne(id);
        GenericEntity<Comment> adapted = new GenericEntity<Comment>(comment) {
        };
        return Response.ok(adapted).build();
    }

    @PermitAll
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments() {
        List<Comment> comments = commentService.findAll();
        GenericEntity<List<Comment>> adapted = new GenericEntity<List<Comment>>(comments) {
        };
        return Response.ok(adapted).build();
    }

    @PermitAll
    @POST
    public Response postComment(Comment comment) {
        commentService.create(comment);
        return  Response.ok().build();
    }

    @PermitAll
    @PUT
    @Path("/update")
    public Response editComment(Comment editedComment) {
        Comment comment = commentService.findOne(editedComment.getId());
        comment.setEmail(editedComment.getEmail());
        comment.setLikes(editedComment.getLikes());
        comment.setName(editedComment.getName());
        comment.setReview(editedComment.getReview());
        commentService.update(comment);

        return  Response.ok().build();
    }


}
