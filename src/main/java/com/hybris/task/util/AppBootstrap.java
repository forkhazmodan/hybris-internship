package com.hybris.task.util;

import com.hybris.task.database.seed.OrderSeed;
import com.hybris.task.database.seed.ProductSeed;

public class AppBootstrap {

    public static void init() {
        AppBootstrap.startDb();
        AppBootstrap.seedDb();
    }

    private static void startDb () {
        HibernateUtil.getSessionFactory().openSession();
    }

    private static void seedDb () {
        ProductSeed.seed();
        OrderSeed.seed();
    }
}
