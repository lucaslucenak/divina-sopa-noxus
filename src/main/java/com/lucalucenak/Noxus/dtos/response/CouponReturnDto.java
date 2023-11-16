package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.CouponFullDto;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class CouponReturnDto {

    private Long id;

    private String description;

    private Double couponValue;

    private Double minimumOrderValue;

    private Integer maxUsages;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CouponReturnDto() {
    }

    public CouponReturnDto(CouponFullDto couponFullDto) {
        BeanUtils.copyProperties(couponFullDto, this);
    }

    public CouponReturnDto(Long id, String description, Double couponValue, Double minimumOrderValue, Integer maxUsages, LocalDateTime startAt, LocalDateTime finishAt, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.couponValue = couponValue;
        this.minimumOrderValue = minimumOrderValue;
        this.maxUsages = maxUsages;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getMaxUsages() {
        return maxUsages;
    }

    public void setMaxUsages(Integer maxUsages) {
        this.maxUsages = maxUsages;
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

    public Double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(Double couponValue) {
        this.couponValue = couponValue;
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
