package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.DeleteProductUseCase;
import com.fiap.techchallenge.domain.repository.ProductRepository;
import com.fiap.techchallenge.interfaces.dto.DeleteProductCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepository repository;

    @Override
    public void deleteProduct(DeleteProductCommand command) {
        log.warn("Deleting product: {}", command.entityId());
        repository.deleteProduct(command.entityId());
    }
}
