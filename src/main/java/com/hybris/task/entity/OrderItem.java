package com.hybris.task.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.product", joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "primaryKey.order", joinColumns = @JoinColumn(name = "order_id"))
})
public class OrderItem {

    private OrderProductId primaryKey = new OrderProductId();

    private Integer quantity = 1;

    public OrderItem() {
    }

    @EmbeddedId
    public OrderProductId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(OrderProductId primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Transient
    public Product getProduct() {
        return getPrimaryKey().getProduct();
    }

    public void setProduct(Product product) {
        getPrimaryKey().setProduct(product);
    }

    @Transient
    public Order getOrder() {
        return getPrimaryKey().getOrder();
    }

    public void setOrder(Order order) {
        getPrimaryKey().setOrder(order);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "primaryKey=" + primaryKey +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) o;

        return getPrimaryKey() != null ? getPrimaryKey().equals(orderItem.getPrimaryKey()) : orderItem.getPrimaryKey() == null;
    }

    @Override
    public int hashCode() {
        return getPrimaryKey() != null ? getPrimaryKey().hashCode() : 0;
    }
}
