package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    boolean existsByDeliveryId(Long deliveryId);
    Optional<OrderModel> findByDeliveryId(Long deliveryId);
}
