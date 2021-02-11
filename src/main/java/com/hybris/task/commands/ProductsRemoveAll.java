package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import com.hybris.task.exceptions.CommandErrorException;
import com.hybris.task.service.ProductService;
import com.hybris.task.util.AppProperties;

@Command(regex = "products:remove.all", description = "Remove all products, only with password")
public class ProductsRemoveAll implements CommandInterface {

    private final AppProperties properties = new AppProperties();
    private final ProductService productService = new ProductService();

    @CommandParam(regex = "password", required = true, example = "12345")
    public String password;

    @Override
    public void run() {
        if(password.equals(properties.getProperty("admin.password"))) {
            productService.removeProducts();
        } else {
            throw new CommandErrorException("Invalid password");
        }
    }
}
