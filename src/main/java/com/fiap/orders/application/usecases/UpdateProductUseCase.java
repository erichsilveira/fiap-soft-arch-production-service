package com.fiap.orders.application.usecases;

import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.interfaces.dto.UpdateProductCommand;
import com.fiap.techchallenge.exception.ResourceNotFoundException;

public interface UpdateProductUseCase {

    Product updateProduct(UpdateProductCommand command) throws ResourceNotFoundException;
}
