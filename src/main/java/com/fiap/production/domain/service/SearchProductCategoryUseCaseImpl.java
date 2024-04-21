package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.SearchProductCategoryUseCase;
import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.domain.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchProductCategoryUseCaseImpl implements SearchProductCategoryUseCase {

    private final ProductCategoryRepository repository;

    @Override
    public List<ProductCategory> searchProductCategories() {
        return repository.searchProductCategories();
    }
}
