package com.fiap.production.infrastructure.adapter;

import java.util.List;
import java.util.stream.StreamSupport;
import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.domain.repository.ProductCategoryRepository;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import com.fiap.production.infrastructure.persistence.ProductCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductCategoryPersistenceAdapter implements ProductCategoryRepository {

    private final ProductCategoryJpaRepository springDataRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        var model = ProductCategoryModel.toProductCategoryModel(productCategory);

        springDataRepository.save(model);

        return ProductCategoryModel.toProductCategory(model);
    }

    @Override
    public List<ProductCategory> searchProductCategories() {
        return StreamSupport.stream(springDataRepository.findAll().spliterator(), false)
            .map(ProductCategoryModel::toProductCategory)
            .toList();
    }
}
