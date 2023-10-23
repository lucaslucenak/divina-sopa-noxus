package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderProductModel;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductModel, OrderProductPk> {

    void deleteByIdOrderId(Long orderId);
    boolean existsByIdOrderId(Long orderId);

    List<Optional<OrderProductModel>> findByIdOrderId(Long orderId);
}
