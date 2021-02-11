package com.hybris.task.commands;

import com.hybris.task.enums.ProductStatus;
import com.hybris.task.exceptions.CommandErrorException;
import com.hybris.task.util.AppProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFacadeTest {

    private final AppProperties properties = new AppProperties();

    @Test
    void run() {
        assertDoesNotThrow(() -> {
            CommandFacade.run(String.format("products:create --name=Test --price=123 --status=%s", ProductStatus.IN_STOCK));
        });
    }

    @Test
    void getCommandInstance() {

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new ProductsCreate(), String.format("products:create --name=Test --price=123 --status=%s", ProductStatus.IN_STOCK)).run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new ProductsGetAll(), "products:get.all").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new ProductsGetOrdered(), "products:get.ordered").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new OrdersCreate(), "orders:create --products=1,2,3").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new OrdersUpdate(), "orders:update --order=1 --add=1,1,2,3 --remove=1,2").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new OrdersGetAll(), "orders:get.all").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new ProductsRemove(), "products:remove --products=1,2,3").run();
        });

        assertDoesNotThrow(() -> {
            CommandFacade.prepareCommand(new ProductsRemoveAll(), String.format("products:remove --password=%s", properties.getProperty("admin.password"))).run();
        });
    }
}