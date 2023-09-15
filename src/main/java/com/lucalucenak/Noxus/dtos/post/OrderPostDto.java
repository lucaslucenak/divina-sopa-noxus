package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderPostDto {

    private String observation;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field streetName shouldn't be null")
    private Long addressId;

    @NotNull(message = "Field streetName shouldn't be null")
    private Long clientAccountId;

    @NotNull(message = "Field streetName shouldn't be null")
    private Long paymentMethodId;

    @NotNull(message = "Field streetName shouldn't be null")
    private Long deliveryTypeId;

    private Map<Long, Integer> soupsIds; // ID | Quantity

    private Map<Long, Integer> drinksIds; // ID | Quantity

    public OrderPostDto() {
    }

    public OrderPostDto(String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, Long addressId, Long clientAccountId, Long paymentMethodId, Long deliveryTypeId, Map<Long, Integer> soupsIds, Map<Long, Integer> drinksIds) {
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.addressId = addressId;
        this.clientAccountId = clientAccountId;
        this.paymentMethodId = paymentMethodId;
        this.deliveryTypeId = deliveryTypeId;
        this.soupsIds = soupsIds;
        this.drinksIds = drinksIds;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public Long getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(Long deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public Map<Long, Integer> getSoupsIdsAndQuantities() {
        return soupsIds;
    }

    public void setSoupsIdsAndQuantities(Map<Long, Integer> soupsIds) {
        this.soupsIds = soupsIds;
    }

    public Map<Long, Integer> getDrinksIdsAndQuantities() {
        return drinksIds;
    }

    public void setDrinksIdsAndQuantities(Map<Long, Integer> drinksIds) {
        this.drinksIds = drinksIds;
    }
}
