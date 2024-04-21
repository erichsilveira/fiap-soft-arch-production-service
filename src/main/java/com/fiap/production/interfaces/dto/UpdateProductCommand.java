package com.fiap.production.interfaces.dto;

import com.fiap.production.domain.entity.Product;

public record UpdateProductCommand(String entityId, Product domainEntity) {

}
