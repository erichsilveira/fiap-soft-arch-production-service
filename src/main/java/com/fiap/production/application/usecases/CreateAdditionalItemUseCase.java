package com.fiap.production.application.usecases;

import com.fiap.production.domain.entity.AdditionalItem;

public interface CreateAdditionalItemUseCase {

    AdditionalItem createAdditionalItem(AdditionalItem command);
}
