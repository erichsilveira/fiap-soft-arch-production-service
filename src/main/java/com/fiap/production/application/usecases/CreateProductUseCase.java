package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.Product;

public interface CreateProductUseCase {

    Product createProduct(Product command);
}
