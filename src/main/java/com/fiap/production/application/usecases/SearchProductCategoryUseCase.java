package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.ProductCategory;
import java.util.List;

public interface SearchProductCategoryUseCase {

    List<ProductCategory> searchProductCategories();
}
