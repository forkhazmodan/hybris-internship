package com.hybris.task.dao;

import com.hybris.task.dto.ProductDto;
import com.hybris.task.entity.Product;

import java.util.List;

public interface ProductDao {

    public Product save(Product product);

    public List<Product> getProducts();

    public List<ProductDto> getProductsOrderedByOrderQuantity();

    public List<Product> getProducts(List<Long> ids);

    public List<Product> getProducts(Long[] ids);

    public void removeProducts(Long[] ids);

    public void removeProducts();
}
