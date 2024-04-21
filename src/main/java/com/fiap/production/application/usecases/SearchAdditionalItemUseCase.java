package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.interfaces.dto.SearchAdditionalItemCommand;
import java.util.List;

public interface SearchAdditionalItemUseCase {

    List<AdditionalItem> searchAdditionalItems(SearchAdditionalItemCommand command);
}
