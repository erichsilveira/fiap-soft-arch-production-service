package com.fiap.orders.infrastructure.persistence;

import com.fiap.techchallenge.infrastructure.model.AdditionalItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalItemJpaRepository extends
    JpaRepository<AdditionalItemModel, String> {

}
