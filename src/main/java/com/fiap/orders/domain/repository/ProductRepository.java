package com.fiap.orders.domain.repository;

import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.exception.ResourceNotFoundException;
import java.util.List;

public interface ProductRepository {

    List<Product> searchProducts(String productCategoryId);

    Product createProduct(Product domainEntity);

    Product updateProduct(String entityId, Product domainEntity) throws ResourceNotFoundException;

    void deleteProduct(String entityId);
}
