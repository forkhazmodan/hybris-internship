package com.hybris.task.entity;

import com.hybris.task.dto.ProductDto;
import com.hybris.task.enums.ProductStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedNativeQuery(
        name = "getProductsOrderedByOrderedQuantityNative",
        query = "select p.name as name" +
                ",p.price as price" +
                ",p.status as status" +
                ",SUM(oi.quantity) as quantity " +
                "from products as p " +
                "right join order_items as oi ON oi.product_id=p.id " +
                "group by p.id " +
                "order by quantity DESC",
        resultSetMapping = "ProductDTOMapping"
)

@SqlResultSetMapping(
        name="ProductDTOMapping",
        classes = {
            @ConstructorResult(
                targetClass = ProductDto.class,
                columns = {
                        @ColumnResult(name="name", type=String.class),
                        @ColumnResult(name="price", type=Integer.class),
                        @ColumnResult(name="quantity", type=Integer.class),
                        @ColumnResult(name="status", type=String.class)}
            )}
)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreationTimestamp
    private Date createdAt;

    @OneToMany(mappedBy = "primaryKey.product", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public Product() {
    }

    public Product(String name, Integer price, ProductStatus status) {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
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
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (this.orderItems.contains(orderItem)) {
            for (OrderItem item : this.orderItems) {
                if (item.equals(orderItem)) {
                    item.setQuantity(item.getQuantity() + 1);
                    return;
                }
            }
        } else {
            this.orderItems.add(orderItem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        return getId() != null ? getId().equals(product.getId()) : product.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
