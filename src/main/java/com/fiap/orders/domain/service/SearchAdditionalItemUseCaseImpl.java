package com.fiap.orders.domain.service;

import java.util.List;
import com.fiap.techchallenge.application.usecases.SearchAdditionalItemUseCase;
import com.fiap.techchallenge.domain.entity.AdditionalItem;
import com.fiap.techchallenge.domain.repository.AdditionalItemRepository;
import com.fiap.techchallenge.interfaces.dto.SearchAdditionalItemCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchAdditionalItemUseCaseImpl implements SearchAdditionalItemUseCase {

    private final AdditionalItemRepository repository;

    @Override
    public List<AdditionalItem> searchAdditionalItems(SearchAdditionalItemCommand command) {
        return repository.searchAdditionalItems(command.productCategoryId());
    }
}
