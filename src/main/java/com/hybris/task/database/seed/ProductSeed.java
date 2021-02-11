package com.hybris.task.database.seed;

import com.hybris.task.dao.ProductDao;
import com.hybris.task.dao.ProductDaoImpl;
import com.hybris.task.entity.Product;
import com.hybris.task.enums.ProductStatus;
import org.apache.commons.math3.random.RandomDataGenerator;

public class ProductSeed {

    public static void seed() {

        ProductDao productDaoImpl = new ProductDaoImpl();

        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName("Test" + (i + 1));
            product.setPrice(new RandomDataGenerator().nextInt(2, 10));
            product.setStatus(ProductStatus.getRandomStatus());
            productDaoImpl.save(product);
        }

    }
}
