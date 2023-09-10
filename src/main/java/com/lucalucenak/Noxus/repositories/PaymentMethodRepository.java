package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.PaymentMethodModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodModel, Long> {
}
