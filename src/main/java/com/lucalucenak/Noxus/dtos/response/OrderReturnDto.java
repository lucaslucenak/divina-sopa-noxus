package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.SoupFullDto;
import com.lucalucenak.Noxus.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderReturnDto {

    private Long id;

    @NotNull(message = "Field orderPrice shouldn't be null")
    @NotEmpty(message = "Field orderPrice shouldn't be empty")
    @NotBlank(message = "Field orderPrice shouldn't be blank")
    private Double orderPrice;

    private String observation;

    private Map<SoupFullDto, Integer> soups;

    private Map<DrinkFullDto, Integer> drinks;

    @NotNull(message = "Field status shouldn't be null")
    private StatusModel status;

    @NotNull(message = "Field address shouldn't be null")
    private AddressModel address;

    @NotNull(message = "Field clientAccount shouldn't be null")
    private ClientAccountModel clientAccount;

    @NotNull(message = "Field paymentMethod shouldn't be null")
    private PaymentMethodModel paymentMethod;

    @NotNull(message = "Field deliveryType shouldn't be null")
    private DeliveryTypeModel deliveryType;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    @NotEmpty(message = "Field dispatchTime shouldn't be empty")
    @NotBlank(message = "Field dispatchTime shouldn't be blank")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field arrivalForecast shouldn't be null")
    @NotEmpty(message = "Field arrivalForecast shouldn't be empty")
    @NotBlank(message = "Field arrivalForecast shouldn't be blank")
    private LocalDateTime arrivalForecast;

    public OrderReturnDto() {
    }

    public OrderReturnDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
    }

    public OrderReturnDto(Long id, Double orderPrice, String observation, Map<SoupFullDto, Integer> soups, Map<DrinkFullDto, Integer> drinks, StatusModel status, AddressModel address, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, DeliveryTypeModel deliveryType, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dispatchTime, LocalDateTime arrivalForecast) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.soups = soups;
        this.drinks = drinks;
        this.status = status;
        this.address = address;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.deliveryType = deliveryType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
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

    public Map<SoupFullDto, Integer> getSoups() {
        return soups;
    }

    public void setSoups(Map<SoupFullDto, Integer> soups) {
        this.soups = soups;
    }

    public Map<DrinkFullDto, Integer> getDrinks() {
        return drinks;
    }

    public void setDrinks(Map<DrinkFullDto, Integer> drinks) {
        this.drinks = drinks;
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
}
