package com.fiap.production.infrastructure.persistence;

import com.fiap.production.infrastructure.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends
    JpaRepository<ProductModel, String> {

}
