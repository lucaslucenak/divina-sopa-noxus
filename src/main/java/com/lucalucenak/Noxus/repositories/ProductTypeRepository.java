package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeModel, Long> {

    Optional<ProductTypeModel> findByType(String type);
}
