package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.SearchProductUseCase;
import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.domain.repository.ProductRepository;
import com.fiap.techchallenge.interfaces.dto.SearchProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchProductUseCaseImpl implements SearchProductUseCase {

    private final ProductRepository repository;

    @Override
    public List<Product> searchProducts(SearchProductCommand command) {
        return repository.searchProducts(command.productCategoryId());
    }
}
