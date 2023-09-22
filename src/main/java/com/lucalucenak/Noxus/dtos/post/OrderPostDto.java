package com.lucalucenak.Noxus.dtos.post;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderPostDto {

    private Long id;

    private String observation;

    private LocalDateTime dispatchTime;

    private LocalDateTime arrivalForecast;

    private Long addressId;

    private Long clientAccountId;

    private Long paymentMethodId;

    private Long deliveryTypeId;

    private Map<Long, Integer> soupsIds; // ID | Quantity

    private Map<Long, Integer> drinksIds; // ID | Quantity

    public OrderPostDto() {
    }

    public OrderPostDto(Long id, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, Long addressId, Long clientAccountId, Long paymentMethodId, Long deliveryTypeId, Map<Long, Integer> soupsIds, Map<Long, Integer> drinksIds) {
        this.id = id;
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

    public Map<Long, Integer> getSoupsIds() {
        return soupsIds;
    }

    public void setSoupsIds(Map<Long, Integer> soupsIds) {
        this.soupsIds = soupsIds;
    }

    public Map<Long, Integer> getDrinksIds() {
        return drinksIds;
    }

    public void setDrinksIds(Map<Long, Integer> drinksIds) {
        this.drinksIds = drinksIds;
    }
}
