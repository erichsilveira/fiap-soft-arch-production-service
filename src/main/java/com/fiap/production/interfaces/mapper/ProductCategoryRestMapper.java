package com.fiap.production.interfaces.mapper;

import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.interfaces.dto.ProductCategoryCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryRestMapper {

    public ProductCategory toDomainEntity(ProductCategoryCreationRequest restEntity) {
        return ProductCategory.builder().name(restEntity.name())
            .description(restEntity.description()).build();
    }
}
