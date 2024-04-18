package com.fiap.orders.infrastructure.persistence;

import com.fiap.techchallenge.infrastructure.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentModel, String> {

}
