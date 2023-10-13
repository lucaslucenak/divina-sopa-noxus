package com.lucalucenak.Noxus.models;

import jakarta.persistence.*;
import lombok.Builder;
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
    @JoinColumn(name = "distance_id")
    private DistanceTaxModel distanceTax;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;

    private Double price;

    private Double distance;
}
