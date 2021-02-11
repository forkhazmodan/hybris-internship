package com.hybris.task.dao;

import com.hybris.task.entity.OrderItem;
import com.hybris.task.entity.Product;
import com.hybris.task.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class OrderItemDaoImpl implements OrderItemDao {

    EntityManager em = HibernateUtil.getEm();

    @Override
    public OrderItem create(OrderItem orderItem) {
        HibernateUtil.performTransaction(() -> {
            em.persist(orderItem);
        });

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return em.createQuery("from OrderItem", OrderItem.class)
                .getResultList();
    }
}
