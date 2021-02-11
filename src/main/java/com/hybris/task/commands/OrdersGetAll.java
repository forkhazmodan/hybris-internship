package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.dto.OrderDto;
import com.hybris.task.service.OrderService;
import de.vandermeer.asciitable.AsciiTable;

import java.text.SimpleDateFormat;
import java.util.List;

@Command(regex = "orders:get.all",description = "Get all products")
public class OrdersGetAll implements CommandInterface {

    private final OrderService orderService = new OrderService();

    @Override
    public void run() {

        List<OrderDto> orders = orderService.getOrdersReport();

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Order ID", "Products total Price", "Product Name", "Products Quantity in orderEntry", "Order Created Date");

        orders.forEach(o -> {
            at.addRule();
            at.addRow(
                o.getId(),
                o.getProductTotalPrice(),
                o.getProductName(),
                o.getOrderItemQuantity(),
                o.getCreateAt(new SimpleDateFormat("YYYY-MM-DD HH:MM"))
            );
        });

        at.addRule();
        System.out.println(at.render());
    }
}
