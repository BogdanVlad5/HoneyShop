package com.honeyshop.resource;

import com.honeyshop.services.ResourceService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.annotation.security.PermitAll;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.image.BufferedImage;
import java.io.*;

@Path("/resource")
public class FileResource {

    @Inject
    private ResourceService resourceService;

    @PermitAll
    @POST
    @Path("/fileupload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response insertImage(@DefaultValue("true") @FormDataParam("enabled") boolean enabled,
                                @FormDataParam("file") InputStream in,
                                @FormDataParam("file") FormDataContentDisposition fileDetail) {
        if (fileDetail == null || fileDetail.getFileName() == null) {
            return Response.noContent().build();
        }
        resourceService.writeImage(fileDetail.getFileName(), in);
        return Response.ok().build();
    }

    @PermitAll
    @GET
    @Path("/{fileName}")
    @Produces({"image/*"})
    public Response getImage(@PathParam("fileName") String fileName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ResourceService.resourceBundle.getString("resource_path")
                    + File.separator + fileName));
            String format = fileName.substring(fileName.lastIndexOf('.') + 1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, format, baos);

            byte[] imageData = baos.toByteArray();
            if (imageData.length == 0) {
                return Response.noContent().build();
            } else {
                return Response.ok(new ByteArrayInputStream(imageData)).build();
            }
        } catch (IOException e) {
            return Response.ok("Something went wrong!").build();
        }
    }
}
