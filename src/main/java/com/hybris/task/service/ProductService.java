package com.hybris.task.service;

import com.hybris.task.dao.ProductDao;
import com.hybris.task.dao.ProductDaoImpl;
import com.hybris.task.dto.ProductDto;
import com.hybris.task.entity.Product;

import java.util.List;

public class ProductService {

    ProductDao productDao = new ProductDaoImpl();

    public void createProduct(Product product) {
        productDao.save(product);
    }

    public List<Product> getProducts(Long[] ids) {
        return productDao.getProducts(ids);
    }

    public void removeProducts(Long[] ids) {
        productDao.removeProducts(ids);
    }

    public void removeProducts() {
        productDao.removeProducts();
    }

    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    public List<ProductDto> getProductsOrderedByOrderQuantity() {
        return productDao.getProductsOrderedByOrderQuantity();
    }
}
