package com.sumadeportes.model.repositories;

import com.sumadeportes.model.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentByPaymentReference(String paymentReference);
}