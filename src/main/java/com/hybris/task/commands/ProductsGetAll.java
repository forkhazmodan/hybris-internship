package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.entity.Product;
import com.hybris.task.service.ProductService;
import de.vandermeer.asciitable.AsciiTable;

import java.util.List;

@Command(regex = "products:get.all", description = "Get all products")
public class ProductsGetAll implements CommandInterface{

    private final ProductService productService = new ProductService();

    @Override
    public void run() {

        List<Product> products = productService.getProducts();

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Product Name", "Product Price", "Product Status");

        products.forEach(product -> {
            at.addRule();
            at.addRow(product.getName(), product.getPrice(), product.getStatus().getDescription());
        });

        at.addRule();
        System.out.println(at.render());
    }
}
