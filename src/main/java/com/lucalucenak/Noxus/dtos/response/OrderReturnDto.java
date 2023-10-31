package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.models.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderReturnDto {

    private Long id;

    private Double orderPrice;

    private Double paidValue;

    private Double change;

    private String observation;

    private List<OrderReturnProductFieldDto> products;

    private StatusModel status;

    private ClientAccountModel clientAccount;

    private PaymentMethodModel paymentMethod;

    private CashRegisterBalanceModel cashRegisterBalance;

    private CouponModel coupon;

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

    public OrderReturnDto(Long id, Double orderPrice, Double paidValue, Double change, String observation, List<OrderReturnProductFieldDto> products, StatusModel status, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, CashRegisterBalanceModel cashRegisterBalance, CouponModel coupon, DeliveryModel delivery, DeliverymanModel deliveryman, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime dispatchTime, LocalDateTime arrivalForecast) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.paidValue = paidValue;
        this.change = change;
        this.observation = observation;
        this.products = products;
        this.status = status;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.cashRegisterBalance = cashRegisterBalance;
        this.coupon = coupon;
        this.delivery = delivery;
        this.deliveryman = deliveryman;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
    }

    public CouponModel getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponModel coupon) {
        this.coupon = coupon;
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

    public Double getPaidValue() { return paidValue; }

    public void setPaidValue(Double paidValue) { this.paidValue = paidValue; }

    public Double getChange() { return change; }

    public void setChange(Double change) { this.change = change; }

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

    public CashRegisterBalanceModel getCashRegisterBalance() {
        return cashRegisterBalance;
    }

    public void setCashRegisterBalance(CashRegisterBalanceModel cashRegisterBalance) {
        this.cashRegisterBalance = cashRegisterBalance;
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
