package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DrinkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkModel, Long> {
}
