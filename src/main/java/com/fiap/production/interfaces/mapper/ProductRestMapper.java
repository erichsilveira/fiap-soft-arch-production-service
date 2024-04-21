package com.fiap.production.interfaces.mapper;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.interfaces.dto.ProductCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRestMapper {

    public Product toDomainEntity(ProductCreationRequest restEntity) {
        return Product.builder().name(restEntity.name())
            .description(restEntity.description()).productCategoryId(restEntity.productCategoryId())
            .price(restEntity.price()).build();
    }
}
