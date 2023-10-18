package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DeliveryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryModel, Long> {
}
