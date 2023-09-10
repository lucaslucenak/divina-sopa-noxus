package com.lucalucenak.Noxus.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "orderr")
@EntityListeners(AuditingEntityListener.class)
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
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private AddressModel address;

    @ManyToOne
    @JoinColumn(name = "client_Account_id", nullable = false)
    private ClientAccountModel clientAccount;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodModel paymentMethod;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryTypeModel deliveryType;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
