package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.CashRegisterBalanceFullDto;
import com.lucalucenak.Noxus.models.CashRegisterBalanceModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class CashRegisterBalanceReturnDto {

    private Long id;

    private Double registeredValue;

    private Double accountedValue;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CashRegisterBalanceReturnDto(){
    }

    public CashRegisterBalanceReturnDto(CashRegisterBalanceModel cashRegisterBalanceModel){
        BeanUtils.copyProperties(cashRegisterBalanceModel, this);
    }

    public CashRegisterBalanceReturnDto(CashRegisterBalanceFullDto cashRegisterBalanceFullDto){
        BeanUtils.copyProperties(cashRegisterBalanceFullDto, this);
    }

    public CashRegisterBalanceReturnDto(Long id, Double registeredValue, Double accountedValue, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
