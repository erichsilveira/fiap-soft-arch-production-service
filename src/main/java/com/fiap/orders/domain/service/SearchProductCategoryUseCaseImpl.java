package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.SearchProductCategoryUseCase;
import com.fiap.techchallenge.domain.entity.ProductCategory;
import com.fiap.techchallenge.domain.repository.ProductCategoryRepository;
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
