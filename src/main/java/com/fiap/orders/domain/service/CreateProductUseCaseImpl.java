package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.CreateProductUseCase;
import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.domain.repository.ProductRepository;
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
