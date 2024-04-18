package com.fiap.orders.application.usecases;

import com.fiap.techchallenge.domain.entity.Product;

public interface CreateProductUseCase {

    Product createProduct(Product command);
}
