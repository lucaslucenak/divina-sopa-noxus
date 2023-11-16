package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.BusinessConfigurationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessConfigurationRepository extends JpaRepository<BusinessConfigurationModel, Long> {
}
