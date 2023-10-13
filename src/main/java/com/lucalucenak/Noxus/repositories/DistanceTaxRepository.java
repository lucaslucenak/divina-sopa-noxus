package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DistanceTaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistanceTaxRepository extends JpaRepository<DistanceTaxModel, Long> {
}
