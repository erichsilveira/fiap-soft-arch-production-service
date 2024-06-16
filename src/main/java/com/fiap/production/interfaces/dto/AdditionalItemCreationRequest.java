package com.fiap.production.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AdditionalItemCreationRequest(@NotBlank @Size(min = 1, max = 100) String name,
                                            @Size(min = 1, max = 1000) String description,
                                            @NotBlank @Size(min = 1, max = 100) String productCategoryId,
                                            @NotNull BigDecimal price) {

}
