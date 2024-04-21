package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.interfaces.dto.UpdateProductCommand;
import com.fiap.production.exception.ResourceNotFoundException;

public interface UpdateProductUseCase {

    Product updateProduct(UpdateProductCommand command) throws ResourceNotFoundException;
}
