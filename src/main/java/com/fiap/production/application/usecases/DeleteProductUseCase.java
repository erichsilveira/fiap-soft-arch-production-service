package com.fiap.production.application.usecases;

import com.fiap.production.interfaces.dto.DeleteProductCommand;

public interface DeleteProductUseCase {

    void deleteProduct(DeleteProductCommand command);
}
