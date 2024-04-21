package com.fiap.production.domain.service;

import com.fiap.production.application.usecases.UpdateProductUseCase;
import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.interfaces.dto.UpdateProductCommand;
import com.fiap.production.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductRepository repository;

    @Override
    public Product updateProduct(UpdateProductCommand command) throws ResourceNotFoundException {
        log.info("Updating product: {}", command.entityId());
        return repository.updateProduct(command.entityId(), command.domainEntity());
    }
}
