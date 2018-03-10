package com.honeyshop.services;

import javax.ejb.Stateless;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

@Stateless
public class ResourceService {

    public static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application", Locale.getDefault());

    public Boolean writeImage(String fileName, InputStream fileContent) {
        String uploadedFileLocation = resourceBundle.getString("resource_path") + File.separator + fileName;
        try {
            OutputStream out;
            int read;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return true;
    }

}
