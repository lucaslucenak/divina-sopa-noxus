package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.OrderFullDto;
import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orderr")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double orderPrice;

    @Column(nullable = true)
    private String observation;

    @Column(nullable = false)
    private LocalDateTime dispatchTime;

    @Column(nullable = false)
    private LocalDateTime arrivalForecast;

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private CouponModel coupon;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccountModel clientAccount;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodModel paymentMethod;

    @OneToOne
    private DeliveryModel delivery;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public OrderModel() {
    }

    public OrderModel(OrderFullDto orderFullDto) {
        BeanUtils.copyProperties(orderFullDto, this);
    }

    public OrderModel(OrderPostDto orderPostDto) {
        BeanUtils.copyProperties(orderPostDto, this);
    }

    public OrderModel(OrderReturnDto orderReturnDto) {
        BeanUtils.copyProperties(orderReturnDto, this);
    }

    public OrderModel(Long id, Double orderPrice, String observation, LocalDateTime dispatchTime, LocalDateTime arrivalForecast, CouponModel coupon, StatusModel status, ClientAccountModel clientAccount, PaymentMethodModel paymentMethod, DeliveryModel delivery, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.observation = observation;
        this.dispatchTime = dispatchTime;
        this.arrivalForecast = arrivalForecast;
        this.coupon = coupon;
        this.status = status;
        this.clientAccount = clientAccount;
        this.paymentMethod = paymentMethod;
        this.delivery = delivery;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CouponModel getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponModel coupon) {
        this.coupon = coupon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
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

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public ClientAccountModel getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccountModel clientAccount) {
        this.clientAccount = clientAccount;
    }

    public PaymentMethodModel getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodModel paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryModel getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryModel delivery) {
        this.delivery = delivery;
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

}
