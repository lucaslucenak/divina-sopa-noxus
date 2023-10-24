package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.DeliveryModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderPostDto {

    private Long id;

    private String observation;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field couponId shouldn't be null")
    private Long couponId;

    @NotNull(message = "Field deliveryId shouldn't be null")
    private Long deliveryId;

    @NotNull(message = "Field clientAccountId shouldn't be null")
    private Long clientAccountId;

    @NotNull(message = "Field paymentMethodId shouldn't be null")
    private Long paymentMethodId;

    @NotNull(message = "Field products shouldn't be null")
    private List<OrderProductPostDto> products;

    public OrderPostDto() {
    }

    public OrderPostDto(Long id, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, Long couponId, Long deliveryId, Long clientAccountId, Long paymentMethodId, List<OrderProductPostDto> products) {
        this.id = id;
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.couponId = couponId;
        this.deliveryId = deliveryId;
        this.clientAccountId = clientAccountId;
        this.paymentMethodId = paymentMethodId;
        this.products = products;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getClientAccountId() {
        return clientAccountId;
    }

    public void setClientAccountId(Long clientAccountId) {
        this.clientAccountId = clientAccountId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public List<OrderProductPostDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductPostDto> products) {
        this.products = products;
    }
}
