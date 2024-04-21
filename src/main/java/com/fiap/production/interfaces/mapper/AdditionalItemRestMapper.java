package com.fiap.production.interfaces.mapper;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.interfaces.dto.AdditionalItemCreationRequest;
import org.springframework.stereotype.Component;

@Component
public class AdditionalItemRestMapper {

    public AdditionalItem toDomainEntity(AdditionalItemCreationRequest restEntity) {
        return AdditionalItem.builder().name(restEntity.name())
            .description(restEntity.description()).productCategoryId(restEntity.productCategoryId())
            .price(restEntity.price()).build();
    }
}
