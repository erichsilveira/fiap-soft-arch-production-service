package com.fiap.production.domain.repository;

import com.fiap.production.domain.entity.AdditionalItem;
import java.util.List;

public interface AdditionalItemRepository {

    List<AdditionalItem> searchAdditionalItems(String productCategoryId);

    AdditionalItem createAdditionalItem(AdditionalItem domainEntity);
}
