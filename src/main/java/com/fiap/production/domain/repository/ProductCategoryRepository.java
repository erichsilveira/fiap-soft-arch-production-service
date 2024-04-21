package com.fiap.production.domain.repository;

import com.fiap.production.domain.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryRepository {

    List<ProductCategory> searchProductCategories();

    ProductCategory createProductCategory(ProductCategory domainEntity);
}
