package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderDrinkModel;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDrinkRepository extends JpaRepository<OrderDrinkModel, OrderDrinkPk> {
}
