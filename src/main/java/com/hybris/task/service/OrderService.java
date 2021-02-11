package com.hybris.task.service;

import com.hybris.task.dao.*;
import com.hybris.task.dto.OrderDto;
import com.hybris.task.entity.Order;
import com.hybris.task.entity.OrderItem;
import com.hybris.task.entity.Product;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    ProductDao productDaoImpl = new ProductDaoImpl();
    OrderDao orderDaoImpl = new OrderDaoImpl();

    public Order createOrderByProductIds(Long[] ids) {
        List<Product> products = productDaoImpl.getProducts(ids);
        List<Product> productsToOrder = new ArrayList<>();

        // Get list of products according to selected product id`s
        // With duplicate
        for (Long id : ids) {
            for (Product product : products) {
                if (id.equals(product.getId())) {
                    productsToOrder.add(product);
                    break;
                }
            }
        }

        return this.createOrder(productsToOrder);
    }

    public Order createOrder(List<Product> products) {

        Order order = new Order();

        products.forEach(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.addOrderItem(orderItem);
        });

        return orderDaoImpl.create(order);
    }

    public Order updateOrder(Long id, List<Product> addProducts, List<Product> removeProducts) throws NoResultException {
        Order order = this.getOrder(id);
        return this.updateOrder(order, addProducts, removeProducts);
    }

    public Order updateOrder(Order order, List<Product> addProducts, List<Product> removeProducts) {

        addProducts.forEach(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.addOrderItem(orderItem);
        });

        removeProducts.forEach(product -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.removeOrderItem(orderItem);
        });

        return orderDaoImpl.update(order);
    }

    public Order getOrder(Long id) {
        return orderDaoImpl.getOrder(id);
    }

    public List<Order> getOrders() {
        return orderDaoImpl.getOrders();
    }

    public List<OrderDto> getOrdersReport() {
        return orderDaoImpl.getOrdersReport();
    }
}
