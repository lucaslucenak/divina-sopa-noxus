package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
