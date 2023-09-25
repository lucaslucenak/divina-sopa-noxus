package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DeliveryTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryTypeModel, Long> {
}
