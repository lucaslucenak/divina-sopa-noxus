package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.OrderDrinkFullDto;
import com.lucalucenak.Noxus.dtos.OrderProductFullDto;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_product")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class OrderProductModel {

    @EmbeddedId
    private OrderProductPk id = new OrderProductPk();

    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public OrderProductModel() {
    }

    public OrderProductModel(OrderProductFullDto orderProductFullDto) {
        BeanUtils.copyProperties(orderProductFullDto, this);
    }

    public OrderProductModel(OrderProductPk id, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
