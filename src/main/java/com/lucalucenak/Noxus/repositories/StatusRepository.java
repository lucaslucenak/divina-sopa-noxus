package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusModel, Long> {
}
