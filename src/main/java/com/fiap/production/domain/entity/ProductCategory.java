package com.fiap.production.domain.entity;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
public class ProductCategory {

    @Getter
    private final String id;

    @Getter
    private final String name;

    @Getter
    private final String description;

    @Getter
    private ZonedDateTime createdAt;

    @Getter
    private ZonedDateTime updatedAt;
}
