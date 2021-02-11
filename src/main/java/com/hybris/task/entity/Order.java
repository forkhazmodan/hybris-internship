package com.hybris.task.entity;

import com.hybris.task.dto.OrderDto;
import com.hybris.task.dto.ProductDto;
import com.sun.istack.Nullable;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@NamedNativeQuery(
        name = "getOrdersReport",
        query = "select o.*" +
                ", p.id as id" +
                ", p.price as productPrice" +
                ", p.name as productName" +
                ", (SUM(p.price) * SUM(oi.quantity)) as productTotalPrice" +
                ", SUM(oi.quantity) as orderItemQuantity" +
                " from orders as o" +
                " left join order_items as oi ON oi.order_id=o.id" +
                " left join products as p ON oi.product_id=p.id" +
                " group by p.id, o.id" +
                " order by o.id",
        resultSetMapping = "OrderDTOMapping"
)

@SqlResultSetMapping(
        name="OrderDTOMapping",
        classes = {
                @ConstructorResult(
                        targetClass = OrderDto.class,
                        columns = {
                                @ColumnResult(name="id", type=Long.class),
                                @ColumnResult(name="productPrice", type=Integer.class),
                                @ColumnResult(name="productTotalPrice", type=Integer.class),
                                @ColumnResult(name="productName", type=String.class),
                                @ColumnResult(name="orderItemQuantity", type=Integer.class),
                                @ColumnResult(name="createdAt", type=Date.class)}
                )}
)
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId = new RandomDataGenerator().nextLong(1L, 10L);

    @Nullable
    private String status;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        orderItems.forEach(this::addOrderItem);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(this.orderItems.contains(orderItem)) {
            for (OrderItem item : this.orderItems) {
                if (item.equals(orderItem)){
                    item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                    return;
                }
            }
        } else {
            this.orderItems.add(orderItem);
        }
    }

    public void removeOrderItem(OrderItem orderItem) {
        if(this.orderItems.contains(orderItem)) {
            for (OrderItem item : this.orderItems) {
                if (item.equals(orderItem)){
                    int totalQuantity = item.getQuantity() - orderItem.getQuantity();
                    if(totalQuantity > 0) {
                        item.setQuantity(totalQuantity);
                    } else {
                        this.orderItems.remove(item);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        orderItems.forEach(v-> {
            sb.append(v.toString());
            sb.append(";");
        });

        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", orderItems=" + sb.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        return getId() != null ? getId().equals(order.getId()) : order.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
