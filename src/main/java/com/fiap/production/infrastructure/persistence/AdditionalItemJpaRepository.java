package com.fiap.production.infrastructure.persistence;

import com.fiap.production.infrastructure.model.AdditionalItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalItemJpaRepository extends
    JpaRepository<AdditionalItemModel, String> {

}
