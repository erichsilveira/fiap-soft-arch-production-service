package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.interfaces.dto.SearchProductByIdCommand;
import com.fiap.production.interfaces.dto.SearchProductCommand;
import java.util.List;

public interface SearchProductUseCase {

    List<Product> searchProducts(SearchProductCommand command);

    Product searchProductById(SearchProductByIdCommand command) throws ResourceNotFoundException;
}
