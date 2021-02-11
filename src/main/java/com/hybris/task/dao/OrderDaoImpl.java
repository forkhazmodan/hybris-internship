package com.hybris.task.dao;

import com.hybris.task.dto.OrderDto;
import com.hybris.task.dto.ProductDto;
import com.hybris.task.entity.Order;
import com.hybris.task.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    EntityManager em = HibernateUtil.getEm();

    @Override
    public Order create(Order order) {
        HibernateUtil.performTransaction(() -> {
            em.persist(order);
        });

        return order;
    }

    @Override
    public Order update(Order order) {
        HibernateUtil.performTransaction(() -> em.merge(order));
        return order;
    }

    @Override
    public List<OrderDto> getOrdersReport() {
        return em.createNamedQuery("getOrdersReport", OrderDto.class)
                .getResultList();
    }

    @Override
    public List<Order> getOrders() {
        return em.createQuery("from Order", Order.class)
                .getResultList();
    }

    @Override
    public Order getOrder(Long id) {
        return em.createQuery("from Order where id = :orderId", Order.class)
                .setParameter("orderId", id)
                .getSingleResult();
    }
}
