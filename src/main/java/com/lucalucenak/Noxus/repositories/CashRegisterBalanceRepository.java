package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.CashRegisterBalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CashRegisterBalanceRepository extends JpaRepository<CashRegisterBalanceModel, Long> {

    Optional<CashRegisterBalanceModel> findTopByCreatedAtBetweenOrderByCreatedAtDesc(LocalDateTime startOfDay, LocalDateTime endOfDay);

    boolean existsByStatusId(Long statusId);
}

