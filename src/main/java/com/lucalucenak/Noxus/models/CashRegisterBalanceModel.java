package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.CashRegisterBalanceFullDto;
import com.lucalucenak.Noxus.dtos.post.CashRegisterBalancePostDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "cash_register_balance")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class CashRegisterBalanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Double registeredValue;

    @Column(nullable = true)
    private Double accountedValue;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public CashRegisterBalanceModel(){
    }

    public CashRegisterBalanceModel(CashRegisterBalanceFullDto cashRegisterBalanceFullDto){
        BeanUtils.copyProperties(cashRegisterBalanceFullDto, this);
    }

    public CashRegisterBalanceModel(CashRegisterBalancePostDto cashRegisterBalanceFullDto){
        BeanUtils.copyProperties(cashRegisterBalanceFullDto, this);
    }
    public CashRegisterBalanceModel(Long id, Double registeredValue, Double accountedValue, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.registeredValue = registeredValue;
        this.accountedValue = accountedValue;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRegisteredValue() {
        return registeredValue;
    }

    public void setRegisteredValue(Double registeredValue) {
        this.registeredValue = registeredValue;
    }

    public Double getAccountedValue() {
        return accountedValue;
    }

    public void setAccountedValue(Double accountedValue) {
        this.accountedValue = accountedValue;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
