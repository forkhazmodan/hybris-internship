package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import com.hybris.task.dao.ProductDao;
import com.hybris.task.dao.ProductDaoImpl;
import com.hybris.task.entity.Product;
import com.hybris.task.enums.ProductStatus;
import com.hybris.task.service.ProductService;

@Command(regex = "products:create", description = "Crate product")
public class ProductsCreate implements CommandInterface {

    private final ProductService productService = new ProductService();

    @CommandParam(regex = "name", required = true, example = "Test")
    public String productName;

    @CommandParam(regex = "price", required = true, example = "123")
    public String productPrice;

    @CommandParam(regex = "status", example = "IN_STOCK|OUT_OF_STOCK|RUNNING_LOW")
    public String productStatus;

    public ProductsCreate() {
    }

    @Override
    public void run() {
        String productName = this.productName;
        Integer productPrice = Integer.parseInt(this.productPrice);
        ProductStatus productStatus;

        try{
            productStatus = ProductStatus.valueOf(this.productStatus);
        } catch (IllegalArgumentException e) {
            productStatus = ProductStatus.IN_STOCK;
        }

        productService.createProduct(new Product(
                productName,
                productPrice,
                productStatus
        ));
    }
}
