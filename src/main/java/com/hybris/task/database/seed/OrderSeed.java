package com.hybris.task.database.seed;

import com.hybris.task.dao.*;
import com.hybris.task.entity.Order;
import com.hybris.task.entity.OrderItem;
import com.hybris.task.entity.Product;
import com.hybris.task.enums.ProductStatus;
import com.hybris.task.service.OrderService;
import com.hybris.task.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderSeed {

    public static void seed() {

        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();

//        Product product1 = new Product(
//                "Test111",
//                111,
//                ProductStatus.IN_STOCK
//        );
//
//        Product product2 = new Product(
//                "Test222",
//                222,
//                ProductStatus.IN_STOCK
//        );
//
//        Product product3 = new Product(
//                "Test333",
//                222,
//                ProductStatus.IN_STOCK
//        );
//
//        productService.createProduct(product1);
//        productService.createProduct(product2);
//        productService.createProduct(product3);
//
//        Long[] ids = {
//                product1.getId(),
//                product1.getId(),
//                product2.getId(),
//                product3.getId(),
//                100500L,
//        };

        List<Product> products = productService.getProducts();

        for (int i = 0; i < 10 ; i++) {
            List<Product> orderProducts = new ArrayList<>();
            Random random = new Random();

            Integer randProductSize = random.ints(1, 4)
                    .findFirst()
                    .getAsInt();

            Integer randDuplicateProductSize = random.ints(1, 4)
                    .findFirst()
                    .getAsInt();

            for (int j = 0; j < randProductSize; j++) {
                Integer randProductId = random.ints(0, products.size() - 1)
                        .findFirst()
                        .getAsInt();

                for (Integer integer = 0; integer < randDuplicateProductSize; integer++) {
                    orderProducts.add(products.get(randProductId));
                }
            }

            orderService.createOrder(orderProducts);
        }


    }
}
