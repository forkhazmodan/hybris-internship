package com.hybris.task.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public enum ProductStatus {
    OUT_OF_STOCK("Out of stock"),
    IN_STOCK("In stock"),
    RUNNING_LOW("Running low");

    String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public static ProductStatus getRandomStatus() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
