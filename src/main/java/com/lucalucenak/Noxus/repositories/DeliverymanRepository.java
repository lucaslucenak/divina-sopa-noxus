package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DeliverymanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<DeliverymanModel, Long> {
}
