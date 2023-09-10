package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.OrderSoupModel;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class OrderSoupFullDto {

    private OrderSoupPk id;

    @NotNull(message = "Field quantity shouldn't be null")
    @NotEmpty(message = "Field quantity shouldn't be empty")
    @NotBlank(message = "Field quantity shouldn't be blank")
    private Integer quantity;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public OrderSoupFullDto() {
    }

    public OrderSoupFullDto(OrderSoupModel orderSoupModel) {
        BeanUtils.copyProperties(orderSoupModel, this);
    }

    public OrderSoupFullDto(OrderSoupPk id, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OrderSoupPk getId() {
        return id;
    }

    public void setId(OrderSoupPk id) {
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
