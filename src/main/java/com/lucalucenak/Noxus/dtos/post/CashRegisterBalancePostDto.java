package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.CashRegisterBalanceModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class CashRegisterBalancePostDto {

    private Long id;

    private Double registeredValue;

    private Double accountedValue;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CashRegisterBalancePostDto(){
    }

    public CashRegisterBalancePostDto(Long id, Double registeredValue, Double accountedValue, Long statusId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.registeredValue = registeredValue;
        this.accountedValue = accountedValue;
        this.statusId = statusId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
