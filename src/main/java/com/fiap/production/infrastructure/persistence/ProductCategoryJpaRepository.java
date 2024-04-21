package com.fiap.production.infrastructure.persistence;

import java.util.UUID;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryJpaRepository extends
    CrudRepository<ProductCategoryModel, UUID> {

}
