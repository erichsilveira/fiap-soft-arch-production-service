package com.fiap.orders.interfaces.mapper;

import com.fiap.techchallenge.domain.entity.AdditionalItem;
import com.fiap.techchallenge.interfaces.dto.AdditionalItemCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class AdditionalItemRestMapper {

    public AdditionalItem toDomainEntity(AdditionalItemCreationRequest restEntity) {
        return AdditionalItem.builder().name(restEntity.name())
            .description(restEntity.description()).productCategoryId(restEntity.productCategoryId())
            .price(restEntity.price()).build();
    }
}
