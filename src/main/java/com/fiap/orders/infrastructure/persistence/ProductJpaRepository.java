package com.fiap.orders.infrastructure.persistence;

import com.fiap.techchallenge.infrastructure.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends
    JpaRepository<ProductModel, String> {

}
