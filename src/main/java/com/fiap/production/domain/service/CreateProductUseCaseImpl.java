package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.CreateProductUseCase;
import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductRepository repository;

    @Override
    public Product createProduct(Product product) {
        log.info("Creating product {} with description {}",
            product.getName(), product.getDescription());
        return repository.createProduct(product);
    }
}
