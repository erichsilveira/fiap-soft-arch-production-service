package com.fiap.orders.interfaces.mapper;

import com.fiap.techchallenge.domain.entity.ProductCategory;
import com.fiap.techchallenge.interfaces.dto.ProductCategoryCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryRestMapper {

    public ProductCategory toDomainEntity(ProductCategoryCreationRequest restEntity) {
        return ProductCategory.builder().name(restEntity.name())
            .description(restEntity.description()).build();
    }
}
