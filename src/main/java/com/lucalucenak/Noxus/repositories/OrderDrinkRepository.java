package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderDrinkModel;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrinkModel, OrderDrinkPk> {

    void deleteByIdOrderId(Long orderId);
    boolean existsByIdOrderId(Long orderId);
}
