package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import com.hybris.task.dao.ProductDaoImpl;
import com.hybris.task.entity.Order;
import com.hybris.task.service.OrderService;
import com.hybris.task.service.ProductService;
import com.hybris.task.util.StringHelper;

@Command(regex = "orders:create", description = "Create orders, use product id`s. Duplicate id entry increase order item quantity.")
public class OrdersCreate implements CommandInterface {

    private final OrderService orderService = new OrderService();

    @CommandParam(regex = "products", required = true, example = "1,1,2,3,3")
    public String productIds;

    @Override
    public void run() {
        orderService.createOrderByProductIds(this.getAddProductIds());
    }

    private Long[] getAddProductIds() {
        return StringHelper.parseStringOfIds(this.productIds);
    }

}
