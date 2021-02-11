package com.hybris.task.service;

import com.hybris.task.BaseTest;
import com.hybris.task.entity.Order;
import com.hybris.task.entity.Product;
import com.hybris.task.enums.ProductStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest extends BaseTest {

    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

    @Test
    public void createOrderByProductIds()
    {

        Product product1 = new Product(
                "Test111",
                111,
                ProductStatus.IN_STOCK
        );

        Product product2 = new Product(
                "Test222",
                222,
                ProductStatus.IN_STOCK
        );

        Product product3 = new Product(
                "Test333",
                222,
                ProductStatus.IN_STOCK
        );

        productService.createProduct(product1);
        productService.createProduct(product2);
        productService.createProduct(product3);

        Long[] ids = {
                product1.getId(),
                product1.getId(),
                product2.getId(),
                product3.getId(),
                100500L,
        };

        Order order = orderService.createOrderByProductIds(ids);

        assertEquals(3, order.getOrderItems().size());
    }

    @Test
    public void createOrder() {
        Product product1 = new Product(
                "Test111",
                111,
                ProductStatus.IN_STOCK
        );

        Product product2 = new Product(
                "Test222",
                222,
                ProductStatus.IN_STOCK
        );

        productService.createProduct(product1);
        productService.createProduct(product2);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product2);
        products.add(product2);

        Order order = orderService.createOrder(products);

        order.getOrderItems().forEach(oi -> {
            if(oi.getPrimaryKey().getProduct().getId().equals(product1.getId())) {
                assertEquals(Integer.valueOf(1), oi.getQuantity());;
            } else if (oi.getPrimaryKey().getProduct().getId().equals(product2.getId())) {
                assertEquals(Integer.valueOf(3), oi.getQuantity());;
            }
        });
    }
}
