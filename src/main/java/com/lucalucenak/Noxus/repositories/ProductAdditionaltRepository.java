package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ProductAdditionalModel;
import com.lucalucenak.Noxus.models.pks.ProductAdditionalPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductAdditionaltRepository extends JpaRepository<ProductAdditionalModel, ProductAdditionalPK> {

    void deleteByIdAdditionalId(Long additionalId);
    boolean existsByIdAdditionalId(Long additionalId);
}
