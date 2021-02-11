package com.hybris.task.dto;

import com.hybris.task.enums.ProductStatus;

public class ProductDto {

    private String name;
    private Integer price;
    private Integer quantity;
    private ProductStatus status;

    public ProductDto() {
    }

    public ProductDto(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProductDto(String name, Integer price, Integer quantity, String status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = ProductStatus.valueOf(status);
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {

        String status = this.getStatus() != null ? this.getStatus().getDescription() : null;

        return "ProductDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }
}
