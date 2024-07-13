package com.fiap.production.interfaces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductCategoryCreationRequest(@NotBlank @Size(min = 1, max = 100) String name,
                                             @Size(min = 1, max = 1000) String description) {

}
