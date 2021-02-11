package com.hybris.task;

import com.hybris.task.service.ProductService;
import com.hybris.task.util.AppBootstrap;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    private final ProductService ps = new ProductService();

    @BeforeClass
    public static void setup() {
        try {
            System.setProperty("PROFILES_ACTIVE", "test");
            AppBootstrap.init();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Test tearDown");
    }
}
