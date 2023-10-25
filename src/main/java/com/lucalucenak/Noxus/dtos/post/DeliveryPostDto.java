package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.enums.DeliveryTaxCalculusEnum;
import jakarta.validation.constraints.NotNull;

public class DeliveryPostDto {

    private Long id;

    private Long addressId;

    private Long deliverymanId;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long deliveryTypeId;

    private DeliveryTaxCalculusEnum deliveryTaxCalculus;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    private Double distance;


    public DeliveryPostDto() {
    }

    public DeliveryPostDto(Long id, Long addressId, Long deliverymanId, Long deliveryTypeId, DeliveryTaxCalculusEnum deliveryTaxCalculus, Long statusId, Double distance) {
        this.id = id;
        this.addressId = addressId;
        this.deliverymanId = deliverymanId;
        this.deliveryTypeId = deliveryTypeId;
        this.deliveryTaxCalculus = deliveryTaxCalculus;
        this.statusId = statusId;
        this.distance = distance;
    }

    public DeliveryTaxCalculusEnum getDeliveryTaxCalculus() {
        return deliveryTaxCalculus;
    }

    public void setDeliveryTaxCalculus(DeliveryTaxCalculusEnum deliveryTaxCalculus) {
        this.deliveryTaxCalculus = deliveryTaxCalculus;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getDeliverymanId() {
        return deliverymanId;
    }

    public void setDeliverymanId(Long deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    public Long getDeliveryTypeId() {
        return deliveryTypeId;
    }

    public void setDeliveryTypeId(Long deliveryTypeId) {
        this.deliveryTypeId = deliveryTypeId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
