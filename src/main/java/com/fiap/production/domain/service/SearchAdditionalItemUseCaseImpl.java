package com.fiap.production.domain.service;

import java.util.List;
import com.fiap.production.application.usecases.SearchAdditionalItemUseCase;
import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.domain.repository.AdditionalItemRepository;
import com.fiap.production.interfaces.dto.SearchAdditionalItemCommand;
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
