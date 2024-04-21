package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.DeleteProductUseCase;
import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.interfaces.dto.DeleteProductCommand;
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
