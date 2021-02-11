package com.hybris.task.service;

import com.hybris.task.BaseTest;
import com.hybris.task.entity.Product;
import com.hybris.task.enums.ProductStatus;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest extends BaseTest {

    private final ProductService productService = new ProductService();

    @Test
    public void createProduct() {

        Product product1 = new Product(
                "Test111",
                111,
                ProductStatus.IN_STOCK
        );

        Product product2 = new Product(
                "Test222",
                222,
                ProductStatus.OUT_OF_STOCK
        );

        productService.createProduct(product1);
        productService.createProduct(product2);

        assertNotNull(product1.getId());
        assertNotNull(product2.getId());
        assertEquals("Test111", product1.getName());
        assertEquals("Test222", product2.getName());
    }

    @Test
    public void getProducts() {

        Product product1 = new Product(
                "Test1",
                111,
                ProductStatus.IN_STOCK
        );

        Product product2 = new Product(
                "Test2",
                222,
                ProductStatus.IN_STOCK
        );

        productService.createProduct(product1);
        productService.createProduct(product2);

        Long[] ids = new Long[]{product1.getId(), product2.getId()};

        List<Product> products = productService.getProducts(ids);
        assertEquals(2, products.size());
        products.forEach(product -> {
            assertThat(product, instanceOf(Product.class));
        });
    }

    @Test
    public void testGetProducts() {
    }
}
