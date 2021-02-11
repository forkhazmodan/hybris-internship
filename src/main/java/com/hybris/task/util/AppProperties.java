package com.hybris.task.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties extends Properties {

    public AppProperties() {

        String profile = "local";

        if(System.getenv("PROFILES_ACTIVE") != null) {
            profile = System.getenv("PROFILES_ACTIVE");
        }

        try {
            this.load(new FileInputStream("src/main/resources/META-INF/" + profile + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
