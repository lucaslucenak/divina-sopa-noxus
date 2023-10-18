package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import com.lucalucenak.Noxus.models.DeliveryModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryTypeReturnDto {

    private Long id;

    private String deliveryType;

    private List<DeliveryModel> deliveries;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DeliveryTypeReturnDto() {
    }

    public DeliveryTypeReturnDto(DeliveryTypeFullDto deliveryTypeFullDto) {
        BeanUtils.copyProperties(deliveryTypeFullDto, this);
    }

    public DeliveryTypeReturnDto(Long id, String deliveryType, List<DeliveryModel> deliveries, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.deliveries = deliveries;
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

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
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
