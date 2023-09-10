package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_soup")
@EntityListeners(AuditingEntityListener.class)
public class OrderSoupModel {

    @EmbeddedId
    private OrderSoupPk id = new OrderSoupPk();

    @Column(nullable = false)
    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private Integer quantity;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public OrderSoupModel() {
    }

    public OrderSoupModel(OrderSoupPk id, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
