package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.PaymentMethodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentMethodFullDto {

    private Long id;

    @NotNull(message = "Field paymentMethod shouldn't be null")
    @NotEmpty(message = "Field paymentMethod shouldn't be empty")
    @NotBlank(message = "Field paymentMethod shouldn't be blank")
    private PaymentMethodEnum paymentMethod;

    @NotNull(message = "Field orders shouldn't be null")
    private List<OrderModel> orders;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public PaymentMethodFullDto() {
    }

    public PaymentMethodFullDto(PaymentMethodModel paymentMethodModel) {
        BeanUtils.copyProperties(paymentMethodModel, this);
    }

    public PaymentMethodFullDto(Long id, PaymentMethodEnum paymentMethod, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.paymentMethod = paymentMethod;
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

    public PaymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
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
