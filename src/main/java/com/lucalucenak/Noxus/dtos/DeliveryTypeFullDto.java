package com.lucalucenak.Noxus.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.DeliveryTypeEnum;
import com.lucalucenak.Noxus.models.DeliveryTypeModel;
import com.lucalucenak.Noxus.models.OrderModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryTypeFullDto {

    private Long id;

    @NotNull(message = "Field deliveryType shouldn't be null")
    @NotEmpty(message = "Field deliveryType shouldn't be empty")
    @NotBlank(message = "Field deliveryType shouldn't be blank")
    private DeliveryTypeEnum deliveryType;

    @NotNull(message = "Field orders shouldn't be null")
    private List<OrderModel> orders;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public DeliveryTypeFullDto() {
    }

    public DeliveryTypeFullDto(DeliveryTypeModel deliveryTypeModel) {
        BeanUtils.copyProperties(deliveryTypeModel, this);
    }

    public DeliveryTypeFullDto(Long id, DeliveryTypeEnum deliveryType, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.orders = orders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryTypeEnum getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeEnum deliveryType) {
        this.deliveryType = deliveryType;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
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
