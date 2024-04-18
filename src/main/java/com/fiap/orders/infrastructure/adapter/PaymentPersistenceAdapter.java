package com.fiap.orders.infrastructure.adapter;

import com.fiap.techchallenge.domain.entity.Payment;
import com.fiap.techchallenge.domain.entity.PaymentStatus;
import com.fiap.techchallenge.domain.repository.PaymentRepository;
import com.fiap.techchallenge.infrastructure.persistence.OrderJpaRepository;
import com.fiap.techchallenge.infrastructure.persistence.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class PaymentPersistenceAdapter implements PaymentRepository {

    private final PaymentJpaRepository springDataRepository;

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Payment getPayment(String paymentId) {
        var model = springDataRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        return PaymentModel.toPayment(model);
    }


    @Override
    public Payment createPayment(String orderId, BigDecimal orderPrice) {
        var model = PaymentModel.builder()
            .order(orderJpaRepository.findById(orderId).orElseThrow())
            .orderPrice(orderPrice)
            .status(PaymentStatus.CREATED)
            .build();

        springDataRepository.save(model);

        return PaymentModel.toPayment(model);
    }

    @Override
    public void updatePayment(String paymentId, PaymentStatus status) {
        var model = springDataRepository.findById(paymentId)
            .orElseThrow(() -> new RuntimeException("Payment not found"));

        model.setStatus(status);

        springDataRepository.save(model);
    }
}
