package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.DistanceTaxModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistanceTaxRepository extends JpaRepository<DistanceTaxModel, Long> {
    boolean existsByInitialDistanceOrFinalDistance(Double initialDistance, Double finalDistance);

    @Query("SELECT dt FROM DistanceTaxModel dt ORDER BY dt.finalDistance DESC")
    Optional<DistanceTaxModel> findTopByFinalDistance();
}
