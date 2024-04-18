package com.fiap.orders.application.usecases;

import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.interfaces.dto.SearchProductCommand;
import java.util.List;

public interface SearchProductUseCase {

    List<Product> searchProducts(SearchProductCommand command);
}
