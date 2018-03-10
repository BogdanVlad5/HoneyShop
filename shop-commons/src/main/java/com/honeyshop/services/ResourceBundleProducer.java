package com.honeyshop.services;

import javax.ws.rs.Produces;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleProducer {

    @Produces
    public ResourceBundle getAppConfiguration(){
        return ResourceBundle.getBundle("application", Locale.getDefault());
    }
}
