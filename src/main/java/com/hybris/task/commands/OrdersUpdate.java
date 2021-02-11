package com.hybris.task.commands;

import com.hybris.task.annotations.Command;
import com.hybris.task.annotations.CommandParam;
import com.hybris.task.entity.Product;
import com.hybris.task.service.OrderService;
import com.hybris.task.service.ProductService;
import com.hybris.task.util.StringHelper;

import javax.persistence.NoResultException;
import java.util.List;

@Command(regex = "orders:update",
        description = "Update order, use product id`s. Duplicate id entry increase/decrease order item quantity.")
public class OrdersUpdate implements CommandInterface {

    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();

    @CommandParam(regex = "order", required = true, example = "1")
    public String order;

    @CommandParam(regex = "add", example = "1,1,2,3")
    public String add;

    @CommandParam(regex = "remove", example = "1,3")
    public String remove;

    @Override
    public void run() {

        Long orderId = Long.parseLong(this.order);
        List<Product> addProducts = productService.getProducts(this.getAddProductIds());
        List<Product> removeProducts = productService.getProducts(this.getRemoveProductIds());

        try {
            orderService.updateOrder(
                    orderId,
                    addProducts,
                    removeProducts);

        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
    }

    private Long[] getAddProductIds() {
        return StringHelper.parseStringOfIds(this.add);
    }

    private Long[] getRemoveProductIds() {
        return StringHelper.parseStringOfIds(this.remove);
    }
}
