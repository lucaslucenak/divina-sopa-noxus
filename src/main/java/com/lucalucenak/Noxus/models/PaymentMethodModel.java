package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payment_method")
@EntityListeners(AuditingEntityListener.class)
public class PaymentMethodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethod;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
