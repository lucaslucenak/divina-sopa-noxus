package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.models.*;
import org.springframework.beans.BeanUtils;

public class DeliveryReturnDto {

    private Long id;

    private AddressModel address;

    private DeliverymanModel deliveryman;

    private DeliveryTypeModel deliveryType;

    private DistanceTaxModel distanceTax;

    private StatusModel status;

    private OrderModel order;

    private Double tax;

    private Double distance;

    public DeliveryReturnDto() {
    }

    public DeliveryReturnDto(DeliveryModel deliveryModel) {
        BeanUtils.copyProperties(deliveryModel, this);
    }

    public DeliveryReturnDto(DeliveryFullDto deliveryFullDto) {
        BeanUtils.copyProperties(deliveryFullDto, this);
    }

    public DeliveryReturnDto(Long id, AddressModel address, DeliverymanModel deliveryman, DeliveryTypeModel deliveryType, DistanceTaxModel distanceTax, StatusModel status, OrderModel order, Double tax, Double distance) {
        this.id = id;
        this.address = address;
        this.deliveryman = deliveryman;
        this.deliveryType = deliveryType;
        this.distanceTax = distanceTax;
        this.status = status;
        this.order = order;
        this.tax = tax;
        this.distance = distance;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
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
