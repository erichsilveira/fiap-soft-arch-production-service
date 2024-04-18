package com.fiap.orders.interfaces.dto;

import com.fiap.techchallenge.domain.entity.Product;

public record UpdateProductCommand(String entityId, Product domainEntity) {

}
