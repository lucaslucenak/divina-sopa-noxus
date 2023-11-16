package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    List<Optional<ProductModel>> findBySizeId(Long sizeId);
}
