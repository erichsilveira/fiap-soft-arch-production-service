package com.fiap.production.infrastructure.adapter;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.domain.repository.AdditionalItemRepository;
import com.fiap.production.infrastructure.model.AdditionalItemModel;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import com.fiap.production.infrastructure.persistence.AdditionalItemJpaRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AdditionalItemPersistenceAdapter implements AdditionalItemRepository {

    private final AdditionalItemJpaRepository springDataRepository;

    @Override
    public AdditionalItem createAdditionalItem(AdditionalItem additionalItem) {
        var model = AdditionalItemModel.toAdditionalItemModel(additionalItem);

        springDataRepository.save(model);

        return AdditionalItemModel.toAdditionalItem(model);
    }

    @Override
    public List<AdditionalItem> searchAdditionalItems(String productCategoryId) {
        var filter = AdditionalItemModel.builder();

        if (StringUtils.isNotBlank(productCategoryId)) {
            filter.productCategory(ProductCategoryModel.builder().id(productCategoryId).build());
        }

        var entities = springDataRepository.findAll(Example.of(filter.build()),
            Sort.by(Sort.Direction.DESC, "name"));

        return entities.stream()
            .map(AdditionalItemModel::toAdditionalItem)
            .toList();
    }
}

