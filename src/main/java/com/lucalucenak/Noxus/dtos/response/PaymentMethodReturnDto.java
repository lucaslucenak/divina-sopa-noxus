package com.lucalucenak.Noxus.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.PaymentMethodFullDto;
import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.PaymentMethodModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentMethodReturnDto {

    private PaymentMethodEnum paymentMethod;

    private List<OrderModel> orders;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PaymentMethodReturnDto() {
    }

    public PaymentMethodReturnDto(PaymentMethodModel paymentMethodModel) {
        BeanUtils.copyProperties(paymentMethodModel, this);
    }

    public PaymentMethodReturnDto(PaymentMethodFullDto paymentMethodFullDto) {
        BeanUtils.copyProperties(paymentMethodFullDto, this);
    }

    public PaymentMethodReturnDto(PaymentMethodEnum paymentMethod, List<OrderModel> orders, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.paymentMethod = paymentMethod;
        this.orders = orders;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
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
