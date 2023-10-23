package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.StatusModel;

import java.time.LocalDateTime;

public class CouponPostDto {

    private Long id;

    private String description;

    private Double value;

    private Double minimumOrderValue;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    private Long statusId;

    public CouponPostDto() {
    }

    public CouponPostDto(Long id, String description, Double value, Double minimumOrderValue, LocalDateTime startAt, LocalDateTime finishAt, Long statusId) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.minimumOrderValue = minimumOrderValue;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.statusId = statusId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
