package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class DeliveryTypePostDto {

    private Long id;

    @NotNull(message = "Field deliveryType shouldn't be null")
    @NotEmpty(message = "Field deliveryType shouldn't be empty")
    @NotBlank(message = "Field deliveryType shouldn't be blank")
    private String deliveryType;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public DeliveryTypePostDto() {
    }

    public DeliveryTypePostDto(Long id, String deliveryType, Long statusId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
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

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
