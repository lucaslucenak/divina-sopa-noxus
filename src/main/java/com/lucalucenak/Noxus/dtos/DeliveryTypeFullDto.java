package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.DeliveryModel;
import com.lucalucenak.Noxus.models.DeliveryTypeModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryTypeFullDto {

    private Long id;

    private String deliveryType;

    private List<DeliveryModel> deliveries;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DeliveryTypeFullDto() {
    }

    public DeliveryTypeFullDto(DeliveryTypeModel deliveryTypeModel) {
        BeanUtils.copyProperties(deliveryTypeModel, this);
    }

    public DeliveryTypeFullDto(Long id, String deliveryType, List<DeliveryModel> deliveries, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.deliveries = deliveries;
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

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
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
