package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeighbourhoodRepository extends JpaRepository<NeighbourhoodModel, Long> {
}
