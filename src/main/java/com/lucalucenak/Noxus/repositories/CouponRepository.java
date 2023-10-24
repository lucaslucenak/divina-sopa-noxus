package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.CouponModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponModel, Long> {
}
