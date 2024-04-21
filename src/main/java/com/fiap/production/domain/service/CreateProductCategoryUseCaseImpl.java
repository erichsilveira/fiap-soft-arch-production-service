package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.CreateProductCategoryUseCase;
import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.domain.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateProductCategoryUseCaseImpl implements CreateProductCategoryUseCase {

    private final ProductCategoryRepository repository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        log.info("Creating ProductCategory {} with description {}",
            productCategory.getName(), productCategory.getDescription());
        return repository.createProductCategory(productCategory);
    }
}
