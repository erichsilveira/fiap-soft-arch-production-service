package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.SearchProductUseCase;
import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.infrastructure.model.ProductModel;
import com.fiap.production.interfaces.dto.SearchProductByIdCommand;
import com.fiap.production.interfaces.dto.SearchProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SearchProductUseCaseImpl implements SearchProductUseCase {

    private final ProductRepository repository;

    @Override
    public List<Product> searchProducts(SearchProductCommand command) {
        return repository.searchProducts(command.productCategoryId());
    }

    @Override
    public Product searchProductById(SearchProductByIdCommand command) throws ResourceNotFoundException {
        log.info("Searching product with id {}", command.id());
        return repository.getProduct(command.id())
                .orElseThrow(ResourceNotFoundException::new);
    }
}
