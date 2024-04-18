package com.fiap.orders.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductUpdateRequest(@NotBlank String name, String description,
                                   @NotBlank String productCategoryId,
                                   @NotNull BigDecimal price) {

}
