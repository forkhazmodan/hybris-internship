package com.hybris.task.dao;

import com.hybris.task.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public OrderItem create(OrderItem orderItem);

    public List<OrderItem> getOrderItems();

}
