package com.fiap.production.domain.repository;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> searchProducts(String productCategoryId);

    public Optional<Product> getProduct(String paymentId);

    Product createProduct(Product domainEntity);

    Product updateProduct(String entityId, Product domainEntity) throws ResourceNotFoundException;

    void deleteProduct(String entityId);
}
