package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.AdditionalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalRepository extends JpaRepository<AdditionalModel, Long> {
}
