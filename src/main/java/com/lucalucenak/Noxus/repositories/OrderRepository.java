package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    boolean existsByDeliveryId(Long deliveryId);
    Optional<OrderModel> findByDeliveryId(Long deliveryId);
    Long countByClientAccountIdAndCouponId(Long clientAccountId, Long couponId);

    @Query("SELECT SUM(o.orderPrice) FROM OrderModel o WHERE o.createdAt BETWEEN :startOfDay AND :endOfDay AND o.status.id = 3")
    Double sumFinishedOrdersOfTheDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}
