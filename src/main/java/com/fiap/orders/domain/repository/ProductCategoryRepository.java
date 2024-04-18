package com.fiap.orders.domain.repository;

import com.fiap.techchallenge.domain.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryRepository {

    List<ProductCategory> searchProductCategories();

    ProductCategory createProductCategory(ProductCategory domainEntity);
}
