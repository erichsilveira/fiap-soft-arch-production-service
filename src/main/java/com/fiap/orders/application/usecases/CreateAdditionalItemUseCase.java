package com.fiap.orders.application.usecases;

import com.fiap.techchallenge.domain.entity.AdditionalItem;

public interface CreateAdditionalItemUseCase {

    AdditionalItem createAdditionalItem(AdditionalItem command);
}
