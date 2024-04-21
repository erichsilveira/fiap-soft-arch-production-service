package com.fiap.production.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@AllArgsConstructor
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
