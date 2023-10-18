package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DeliverymanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliverymanRepository extends JpaRepository<DeliverymanModel, Long> {
    boolean existsByName(String deliverymanName);
    Optional<DeliverymanModel> findByName(String deliverymanName);
}
