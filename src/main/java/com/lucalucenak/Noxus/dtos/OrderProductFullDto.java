package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.OrderProductModel;
import com.lucalucenak.Noxus.models.OrderSoupModel;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class OrderProductFullDto {

    private OrderProductPk id;

    private Integer quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public OrderProductFullDto() {
    }

    public OrderProductFullDto(OrderProductModel orderSoupModel) {
        BeanUtils.copyProperties(orderSoupModel, this);
    }

    public OrderProductFullDto(OrderProductPk id, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OrderProductFullDto(OrderProductPk id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public OrderProductPk getId() {
        return id;
    }

    public void setId(OrderProductPk id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
