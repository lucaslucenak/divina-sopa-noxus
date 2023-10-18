package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.DeliveryFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryPostDto;
import com.lucalucenak.Noxus.dtos.response.DeliveryReturnDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "delivery")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DeliveryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressModel address;

    @ManyToOne
    @JoinColumn(name = "deliveryman_id")
    private DeliverymanModel deliveryman;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryTypeModel deliveryType;

    @ManyToOne
    @JoinColumn(name = "distance_tax_id")
    private DistanceTaxModel distanceTax;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", cascade = CascadeType.ALL)
    private OrderModel order;

    private Double tax;

    private Double distance;

    public DeliveryModel() {
    }

    public DeliveryModel(DeliveryPostDto deliveryPostDto) {
        BeanUtils.copyProperties(deliveryPostDto, this);
    }

    public DeliveryModel(DeliveryFullDto deliveryFullDto) {
        BeanUtils.copyProperties(deliveryFullDto, this);
    }

    public DeliveryModel(DeliveryReturnDto deliveryReturnDto) {
        BeanUtils.copyProperties(deliveryReturnDto, this);
    }

    public DeliveryModel(Long id, AddressModel address, DeliverymanModel deliveryman, DeliveryTypeModel deliveryType, DistanceTaxModel distanceTax, StatusModel status, OrderModel order, Double tax, Double distance) {
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
