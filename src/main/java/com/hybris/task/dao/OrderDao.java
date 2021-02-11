package com.hybris.task.dao;

import com.hybris.task.dto.OrderDto;
import com.hybris.task.entity.Order;

import java.util.List;

public interface OrderDao {

    public Order create(Order order);

    public Order update(Order order);

    public List<OrderDto> getOrdersReport();

    public List<Order> getOrders();

    public Order getOrder(Long id);
}
