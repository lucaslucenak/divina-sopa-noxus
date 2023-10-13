package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

public class DeliveryFullDto {

    private Long id;

    private AddressModel address;

    private DeliverymanModel deliveryman;

    private DeliveryTypeModel deliveryType;

    private DistanceTaxModel distanceTax;

    private StatusModel status;

    private Double price;

    private Double distance;

    public DeliveryFullDto() {
    }

    public DeliveryFullDto(Long id, AddressModel address, DeliverymanModel deliveryman, DeliveryTypeModel deliveryType, DistanceTaxModel distanceTax, StatusModel status, Double price, Double distance) {
        this.id = id;
        this.address = address;
        this.deliveryman = deliveryman;
        this.deliveryType = deliveryType;
        this.distanceTax = distanceTax;
        this.status = status;
        this.price = price;
        this.distance = distance;
    }

    public DeliveryFullDto(DeliveryModel deliveryModel) {
        BeanUtils.copyProperties(deliveryModel, this);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
