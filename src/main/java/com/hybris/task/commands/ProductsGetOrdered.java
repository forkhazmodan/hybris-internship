package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.dto.ProductDto;
import com.hybris.task.service.ProductService;
import de.vandermeer.asciitable.AsciiTable;

import java.util.List;

@Command(regex = "products:get.ordered", description = "Get ordered products")
public class ProductsGetOrdered implements CommandInterface {

    private final ProductService productService = new ProductService();

    @Override
    public void run() {

        List<ProductDto> products = productService.getProductsOrderedByOrderQuantity();

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Product Name", "Product Price", "Product Status", "Order Quantity");

        products.forEach(productDto -> {
            at.addRule();
            at.addRow(productDto.getName(), productDto.getPrice(), productDto.getStatus().getDescription(), productDto.getQuantity());
        });

        at.addRule();
        System.out.println(at.render());
    }
}
