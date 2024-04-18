package com.fiap.orders.domain.service;

import com.fiap.techchallenge.application.usecases.CreateAdditionalItemUseCase;
import com.fiap.techchallenge.domain.entity.AdditionalItem;
import com.fiap.techchallenge.domain.repository.AdditionalItemRepository;
import com.fiap.techchallenge.infrastructure.model.AdditionalItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateAdditionalItemUseCaseImpl implements CreateAdditionalItemUseCase {

    private final AdditionalItemRepository repository;

    @Override
    public AdditionalItem createAdditionalItem(AdditionalItem additionalItem) {
        log.info("Creating AdditionalItem: {}", additionalItem.getName());
        return repository.createAdditionalItem(additionalItem);
    }
}
