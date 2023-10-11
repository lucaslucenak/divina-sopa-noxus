package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.SoupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SoupRepository extends JpaRepository<SoupModel, Long> {
    List<Optional<SoupModel>> findBySizeId(Long sizeId);
}
