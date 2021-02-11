package com.hybris.task.dao;

import com.hybris.task.dto.ProductDto;
import com.hybris.task.entity.Product;
import com.hybris.task.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    EntityManager em = HibernateUtil.getEm();

    @Override
    public Product save(Product product) {
        HibernateUtil.performTransaction(() -> {
            em.persist(product);
        });

        return product;
    }

    @Override
    public List<Product> getProducts() {
        return em.createQuery("from Product", Product.class)
                .getResultList();
    }

    @Override
    public List<ProductDto> getProductsOrderedByOrderQuantity() {
        return em.createNamedQuery("getProductsOrderedByOrderedQuantityNative", ProductDto.class)
                .getResultList();
    }

    @Override
    public List<Product> getProducts(List<Long> ids) {
        return em.createQuery("from Product WHERE id IN (:ids)", Product.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Product> getProducts(Long[] ids) {
        List<Long> targetList = Arrays.asList(ids);
        return this.getProducts(targetList);
    }

    @Override
    public void removeProducts(Long[] ids) {
        HibernateUtil.performTransaction(() -> {
            List<Long> targetList = Arrays.asList(ids);
            em.createQuery("delete from Product WHERE id IN (:ids)")
                    .setParameter("ids", targetList)
                    .executeUpdate();
        });

    }

    @Override
    public void removeProducts() {
        HibernateUtil.performTransaction(() -> {
            em.createQuery("delete from Product")
                    .executeUpdate();
        });
    }


}
