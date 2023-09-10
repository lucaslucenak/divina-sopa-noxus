package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class OrderFullDto {

    private Long id;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private Double orderPrice;

    private String observation;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field streetName shouldn't be null")
    private StatusModel status;

    @NotNull(message = "Field streetName shouldn't be null")
    private AddressModel address;

    @NotNull(message = "Field streetName shouldn't be null")
    private ClientAccountModel clientAccount;

    @NotNull(message = "Field streetName shouldn't be null")
    private PaymentMethodModel paymentMethod;

    @NotNull(message = "Field streetName shouldn't be null")
    private DeliveryTypeModel deliveryType;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime updatedAt;

    public OrderFullDto() {
    }

    public OrderFullDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
    }

    public OrderFullDto(Long id, Double orderPrice, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, StatusModel status, AddressModel address, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, DeliveryTypeModel deliveryType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.status = status;
        this.address = address;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
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

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
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

    public DeliveryTypeModel getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeModel deliveryType) {
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
