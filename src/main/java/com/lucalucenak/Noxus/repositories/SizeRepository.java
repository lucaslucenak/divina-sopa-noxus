package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.SizeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<SizeModel, Long> {
}
