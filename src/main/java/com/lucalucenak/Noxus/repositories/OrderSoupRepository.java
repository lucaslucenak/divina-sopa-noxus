package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderSoupModel;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSoupRepository extends JpaRepository<OrderSoupModel, OrderSoupPk> {
}
