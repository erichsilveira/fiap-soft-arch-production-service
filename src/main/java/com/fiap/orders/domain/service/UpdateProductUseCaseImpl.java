package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.UpdateProductUseCase;
import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.domain.repository.ProductRepository;
import com.fiap.techchallenge.interfaces.dto.UpdateProductCommand;
import com.fiap.techchallenge.exception.ResourceNotFoundException;
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
