package com.fiap.orders.interfaces.dto;

import jakarta.validation.constraints.NotBlank;

public record ProductCategoryCreationRequest(@NotBlank String name, String description) {

}
