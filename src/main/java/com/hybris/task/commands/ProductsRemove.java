package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import com.hybris.task.service.ProductService;
import com.hybris.task.util.StringHelper;

@Command(regex = "products:remove", description = "Remove chosen products")
public class ProductsRemove implements CommandInterface {

    private final ProductService productService = new ProductService();

    @CommandParam(regex = "products", required = true, example = "1,2,3")
    public String productIds;

    @Override
    public void run() {
        productService.removeProducts(getProductIds());
    }

    private Long[] getProductIds() {
        return StringHelper.parseStringOfIds(this.productIds);
    }

}
