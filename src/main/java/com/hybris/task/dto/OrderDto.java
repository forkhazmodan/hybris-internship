package com.hybris.task.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderDto {

    Long id;
    Integer productPrice;
    Integer productTotalPrice;
    String productName;
    Integer orderItemQuantity;
    Date createdAt;

    public OrderDto(Long id, Integer productPrice, Integer productTotalPrice, String productName, Integer orderItemQuantity, Date createdAt) {
        this.id = id;
        this.productPrice = productPrice;
        this.productTotalPrice = productTotalPrice;
        this.productName = productName;
        this.orderItemQuantity = orderItemQuantity;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Integer productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(Integer orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public String getCreateAt(SimpleDateFormat pattern) {
        return pattern.format(createdAt);
    }

    public void setCreateAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", productPrice=" + productPrice +
                ", orderPrice=" + productTotalPrice +
                ", productName='" + productName + '\'' +
                ", orderItemQuantity=" + orderItemQuantity +
                ", createAt=" + createdAt +
                '}';
    }
}
