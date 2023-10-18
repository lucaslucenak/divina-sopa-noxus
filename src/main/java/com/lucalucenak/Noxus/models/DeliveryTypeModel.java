package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "delivery_type")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DeliveryTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Field deliveryType shouldn't be null")
    private String deliveryType;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DeliveryTypeModel() {
    }

    public DeliveryTypeModel(DeliveryTypeFullDto deliveryTypeFullDto) {
        BeanUtils.copyProperties(deliveryTypeFullDto, this);
    }

    public DeliveryTypeModel(Long id, String deliveryType, List<DeliveryModel> deliveries, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.deliveries = deliveries;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void upperCaseName() {
        if (deliveryType != null) {
            deliveryType = deliveryType.toUpperCase(Locale.ROOT);
        }
    }

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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
