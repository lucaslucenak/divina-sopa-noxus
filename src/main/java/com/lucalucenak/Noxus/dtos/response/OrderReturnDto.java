package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DrinkFullDto;
import com.lucalucenak.Noxus.dtos.SoupFullDto;
import com.lucalucenak.Noxus.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderReturnDto {

    private Long id;

    @NotNull(message = "Field orderPrice shouldn't be null")
    @NotEmpty(message = "Field orderPrice shouldn't be empty")
    @NotBlank(message = "Field orderPrice shouldn't be blank")
    private Double orderPrice;

    private String observation;

    private List<OrderReturnSoupFieldDto> soups;
//    private Map<SoupFullDto, Integer> soups;

    List<OrderReturnDrinkFieldDto> drinks;
//    private Map<DrinkFullDto, Integer> drinks;

    @NotNull(message = "Field status shouldn't be null")
    private StatusModel status;

    @NotNull(message = "Field address shouldn't be null")
    private AddressModel address;

    @NotNull(message = "Field clientAccount shouldn't be null")
    private ClientAccountModel clientAccount;

    @NotNull(message = "Field paymentMethod shouldn't be null")
    private PaymentMethodModel paymentMethod;

    @NotNull(message = "Field delivery shouldn't be null")
    private DeliveryModel delivery;

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

    public OrderReturnDto(Long id, Double orderPrice, String observation, List<OrderReturnSoupFieldDto> soups, List<OrderReturnDrinkFieldDto> drinks, StatusModel status, AddressModel address, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, DeliveryModel delivery, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dispatchTime, LocalDateTime arrivalForecast) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.soups = soups;
        this.drinks = drinks;
        this.status = status;
        this.address = address;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.delivery = delivery;
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

    public List<OrderReturnSoupFieldDto> getSoups() {
        return soups;
    }

    public void setSoups(List<OrderReturnSoupFieldDto> soups) {
        this.soups = soups;
    }

    public List<OrderReturnDrinkFieldDto> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<OrderReturnDrinkFieldDto> drinks) {
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

    public DeliveryModel getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryModel delivery) {
        this.delivery = delivery;
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
