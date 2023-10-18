package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class OrderFullDto {

    private Long id;

    @NotNull(message = "Field orderPrice shouldn't be null")
    private Double orderPrice;

    private String observation;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field arrivalForecast shouldn't be null")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field status shouldn't be null")
    private StatusModel status;

    @NotNull(message = "Field delivery shouldn't be null")
    private DeliveryModel delivery;

//    private DeliverymanModel deliveryman;

    @NotNull(message = "Field clientAccount shouldn't be null")
    private ClientAccountModel clientAccount;

    @NotNull(message = "Field paymentMethod shouldn't be null")
    private PaymentMethodModel paymentMethod;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public OrderFullDto() {
    }

    public OrderFullDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
    }

    public OrderFullDto(Long id, Double orderPrice, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, StatusModel status, DeliveryModel delivery, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.status = status;
        this.delivery = delivery;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDateTime getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(LocalDateTime dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public LocalDateTime getArrivalForecast() {
        return arrivalForecast;
    }

    public void setArrivalForecast(LocalDateTime arrivalForecast) {
        this.arrivalForecast = arrivalForecast;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public DeliveryModel getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryModel delivery) {
        this.delivery = delivery;
    }

    public ClientAccountModel getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccountModel clientAccount) {
        this.clientAccount = clientAccount;
    }

    public PaymentMethodModel getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodModel paymentMethod) {
        this.paymentMethod = paymentMethod;
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
