package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.DeliveryModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderPostDto {

    private Long id;

    private String observation;

    private Long couponId;

    private Double paidValue;

    @NotNull(message = "Field deliveryId shouldn't be null")
    private Long deliveryId;

    @NotNull(message = "Field clientAccountId shouldn't be null")
    private Long clientAccountId;

    @NotNull(message = "Field paymentMethodId shouldn't be null")
    private Long paymentMethodId;

    @NotNull(message = "Field paymentMethodId shouldn't be null")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field products shouldn't be null")
    private List<OrderProductPostDto> products;

    public OrderPostDto() {
    }

    public OrderPostDto(Long id, String observation, Long couponId, Double paidValue, Long deliveryId, Long clientAccountId, Long paymentMethodId, LocalDateTime arrivalForecast, List<OrderProductPostDto> products) {
        this.id = id;
        this.observation = observation;
        this.couponId = couponId;
        this.paidValue = paidValue;
        this.deliveryId = deliveryId;
        this.clientAccountId = clientAccountId;
        this.paymentMethodId = paymentMethodId;
        this.arrivalForecast = arrivalForecast;
        this.products = products;
    }

    public LocalDateTime getArrivalForecast() {
        return arrivalForecast;
    }

    public void setArrivalForecast(LocalDateTime arrivalForecast) {
        this.arrivalForecast = arrivalForecast;
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

    public Double getPaidValue() { return paidValue; }

    public void setPaidValue(Double paidValue) { this.paidValue = paidValue; }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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
