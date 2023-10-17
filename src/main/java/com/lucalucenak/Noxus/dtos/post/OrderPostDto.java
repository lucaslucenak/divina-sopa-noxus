package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.DeliveryModel;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Map;

public class OrderPostDto {

    private Long id;

    private String observation;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    private LocalDateTime dispatchTime;

    @NotNull(message = "Field dispatchTime shouldn't be null")
    private LocalDateTime arrivalForecast;

    @NotNull(message = "Field delivery shouldn't be null")
    private DeliveryPostDto delivery;

    @NotNull(message = "Field clientAccountId shouldn't be null")
    private Long clientAccountId;

    @NotNull(message = "Field paymentMethodId shouldn't be null")
    private Long paymentMethodId;

    private Map<Long, Integer> soupsIds; // ID | Quantity

    private Map<Long, Integer> drinksIds; // ID | Quantity

    public OrderPostDto() {
    }

    public OrderPostDto(Long id, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, DeliveryPostDto delivery, Long clientAccountId, Long paymentMethodId, Map<Long, Integer> soupsIds, Map<Long, Integer> drinksIds) {
        this.id = id;
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.delivery = delivery;
        this.clientAccountId = clientAccountId;
        this.paymentMethodId = paymentMethodId;
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

    public DeliveryPostDto getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryPostDto delivery) {
        this.delivery = delivery;
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
