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
    @JoinColumn(name = "deliveryman_id", nullable = false)
    private DeliverymanModel deliveryman;

    @ManyToOne
    @JoinColumn(name = "delivery_type_id", nullable = false)
    private DeliveryTypeModel deliveryType;

    private Double price;

    private Double distance;
}
