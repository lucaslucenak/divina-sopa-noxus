package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotNull;

public class DeliveryPostDto {

    private Long id;

    private Long addressId;

    private Long deliverymanId;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long deliveryTypeId;

    private Long distanceTaxId;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public DeliveryPostDto() {
    }

    public DeliveryPostDto(Long id, Long addressId, Long deliverymanId, Long deliveryTypeId, Long distanceTaxId, Long statusId) {
        this.id = id;
        this.addressId = addressId;
        this.deliverymanId = deliverymanId;
        this.deliveryTypeId = deliveryTypeId;
        this.distanceTaxId = distanceTaxId;
        this.statusId = statusId;
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

    public Long getDistanceTaxId() {
        return distanceTaxId;
    }

    public void setDistanceTaxId(Long distanceTaxId) {
        this.distanceTaxId = distanceTaxId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}