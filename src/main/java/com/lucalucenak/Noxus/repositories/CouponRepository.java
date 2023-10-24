package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<CouponModel, Long> {

    @Query("SELECT c FROM CouponModel c WHERE DATE(c.finishAt) = CURRENT_DATE")
    List<Optional<CouponModel>> findAllWithFinishDateEqualsToday();
}
