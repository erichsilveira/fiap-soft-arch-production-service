package com.fiap.orders.application.usecases;

import com.fiap.techchallenge.domain.entity.AdditionalItem;
import com.fiap.techchallenge.interfaces.dto.SearchAdditionalItemCommand;
import java.util.List;

public interface SearchAdditionalItemUseCase {

    List<AdditionalItem> searchAdditionalItems(SearchAdditionalItemCommand command);
}
