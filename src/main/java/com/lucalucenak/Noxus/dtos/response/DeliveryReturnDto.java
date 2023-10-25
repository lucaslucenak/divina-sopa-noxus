package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.enums.DeliveryTaxCalculusEnum;
import com.lucalucenak.Noxus.models.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class DeliveryReturnDto {

    private Long id;

    private AddressModel address;

    private DeliverymanModel deliveryman;

    private DeliveryTypeModel deliveryType;

    private DistanceTaxModel distanceTax;

    private DeliveryTaxCalculusEnum deliveryTaxCalculus;

    private StatusModel status;

    private Double tax;

    private Double distance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DeliveryReturnDto() {
    }

    public DeliveryReturnDto(DeliveryModel deliveryModel) {
        BeanUtils.copyProperties(deliveryModel, this);
    }

    public DeliveryReturnDto(DeliveryFullDto deliveryFullDto) {
        BeanUtils.copyProperties(deliveryFullDto, this);
    }

    public DeliveryReturnDto(Long id, AddressModel address, DeliverymanModel deliveryman, DeliveryTypeModel deliveryType, DistanceTaxModel distanceTax, DeliveryTaxCalculusEnum deliveryTaxCalculus, StatusModel status, Double tax, Double distance, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.address = address;
        this.deliveryman = deliveryman;
        this.deliveryType = deliveryType;
        this.distanceTax = distanceTax;
        this.deliveryTaxCalculus = deliveryTaxCalculus;
        this.status = status;
        this.tax = tax;
        this.distance = distance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DeliveryTaxCalculusEnum getDeliveryTaxCalculus() {
        return deliveryTaxCalculus;
    }

    public void setDeliveryTaxCalculus(DeliveryTaxCalculusEnum deliveryTaxCalculus) {
        this.deliveryTaxCalculus = deliveryTaxCalculus;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public DeliverymanModel getDeliveryman() {
        return deliveryman;
    }

    public void setDeliveryman(DeliverymanModel deliveryman) {
        this.deliveryman = deliveryman;
    }

    public DeliveryTypeModel getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryTypeModel deliveryType) {
        this.deliveryType = deliveryType;
    }

    public DistanceTaxModel getDistanceTax() {
        return distanceTax;
    }

    public void setDistanceTax(DistanceTaxModel distanceTax) {
        this.distanceTax = distanceTax;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
