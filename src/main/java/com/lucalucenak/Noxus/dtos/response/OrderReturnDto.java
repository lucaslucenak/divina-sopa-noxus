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

    private Double orderPrice;

    private String observation;

    private List<OrderReturnProductFieldDto> products;

    private StatusModel status;

    private AddressModel address;

    private ClientAccountModel clientAccount;

    private PaymentMethodModel paymentMethod;

    private DeliveryModel delivery;

    private DeliverymanModel deliveryman;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime dispatchTime;

    private LocalDateTime arrivalForecast;

    public OrderReturnDto() {
    }

    public OrderReturnDto(OrderModel orderModel) {
        BeanUtils.copyProperties(orderModel, this);
    }

    public OrderReturnDto(Long id, Double orderPrice, String observation, List<OrderReturnProductFieldDto> products, StatusModel status, AddressModel address, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, DeliveryModel delivery, DeliverymanModel deliveryman, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dispatchTime, LocalDateTime arrivalForecast) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.products = products;
        this.status = status;
        this.address = address;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.delivery = delivery;
        this.deliveryman = deliveryman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
    }

    public DeliverymanModel getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(DeliverymanModel deliveryman) {
        this.deliveryman = deliveryman;
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

    public List<OrderReturnProductFieldDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderReturnProductFieldDto> products) {
        this.products = products;
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
