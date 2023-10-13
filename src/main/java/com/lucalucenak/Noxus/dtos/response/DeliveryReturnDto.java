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

    private Double price;

    private Double distance;

    public DeliveryReturnDto() {
    }

    public DeliveryReturnDto(DeliveryModel deliveryModel) {
        BeanUtils.copyProperties(deliveryModel, this);
    }

    public DeliveryReturnDto(DeliveryFullDto deliveryFullDto) {
        BeanUtils.copyProperties(deliveryFullDto, this);
    }

    public DeliveryReturnDto(Long id, AddressModel address, DeliverymanModel deliveryman, DeliveryTypeModel deliveryType, DistanceTaxModel distanceTax, StatusModel status, Double price, Double distance) {
        this.id = id;
        this.address = address;
        this.deliveryman = deliveryman;
        this.deliveryType = deliveryType;
        this.distanceTax = distanceTax;
        this.status = status;
        this.price = price;
        this.distance = distance;
    }
}
