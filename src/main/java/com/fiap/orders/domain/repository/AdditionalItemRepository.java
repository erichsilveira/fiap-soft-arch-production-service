package com.fiap.orders.domain.repository;

import com.fiap.techchallenge.domain.entity.AdditionalItem;
import java.util.List;

public interface AdditionalItemRepository {

    List<AdditionalItem> searchAdditionalItems(String productCategoryId);

    AdditionalItem createAdditionalItem(AdditionalItem domainEntity);
}
