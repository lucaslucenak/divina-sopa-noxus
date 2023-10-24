package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.dtos.response.CouponReturnDto;
import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.CouponModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class CouponFullDto {

    private Long id;

    private String description;

    private Double value;

    private Double minimumOrderValue;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CouponFullDto() {
    }

    public CouponFullDto(CouponModel additionalModel) {
        BeanUtils.copyProperties(additionalModel, this);
    }

    public CouponFullDto(CouponReturnDto additionalReturnDto) {
        BeanUtils.copyProperties(additionalReturnDto, this);
    }

    public CouponFullDto(Long id, String description, Double value, Double minimumOrderValue, LocalDateTime startAt, LocalDateTime finishAt, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.minimumOrderValue = minimumOrderValue;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(Double minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDateTime finishAt) {
        this.finishAt = finishAt;
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
