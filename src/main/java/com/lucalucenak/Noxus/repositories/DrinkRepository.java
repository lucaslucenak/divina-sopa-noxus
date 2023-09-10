package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkRepository extends JpaRepository<DrinkModel, Long> {
}
