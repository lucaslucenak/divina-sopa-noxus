package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.DeliveryTypeEnum;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "delivery_type")
@EntityListeners(AuditingEntityListener.class)
public class DeliveryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryTypeEnum deliveryType;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
