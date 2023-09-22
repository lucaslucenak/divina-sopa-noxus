package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderDrinkModel;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrinkModel, OrderDrinkPk> {

    void deleteByIdOrderId(Long orderId);
    boolean existsByIdOrderId(Long orderId);

    List<Optional<OrderDrinkModel>> findByIdOrderId(Long orderId);
}
