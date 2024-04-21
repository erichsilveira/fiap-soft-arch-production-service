package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.ProductCategory;

public interface CreateProductCategoryUseCase {

    ProductCategory createProductCategory(ProductCategory command);
}
