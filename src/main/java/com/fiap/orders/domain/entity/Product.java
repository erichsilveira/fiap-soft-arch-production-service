package com.fiap.orders.domain.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
public class Product {

    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private String description;

    @Getter
    private final BigDecimal price;

    @Getter
    private final String productCategoryId;

    @Getter
    private ZonedDateTime createdAt;

    @Getter
    private ZonedDateTime updatedAt;
}
