package com.fiap.orders.interfaces.mapper;

import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.interfaces.dto.ProductCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRestMapper {

    public Product toDomainEntity(ProductCreationRequest restEntity) {
        return Product.builder().name(restEntity.name())
            .description(restEntity.description()).productCategoryId(restEntity.productCategoryId())
            .price(restEntity.price()).build();
    }
}
