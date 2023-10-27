package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.DeliveryTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliveryTypePostDto;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
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
    private String deliveryType;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DeliveryTypeModel() {
    }

    public DeliveryTypeModel(DeliveryTypeFullDto deliveryTypeFullDto) {
        BeanUtils.copyProperties(deliveryTypeFullDto, this);
    }

    public DeliveryTypeModel(DeliveryTypePostDto deliveryTypePostDto) {
        BeanUtils.copyProperties(deliveryTypePostDto, this);
    }

    public DeliveryTypeModel(Long id, String deliveryType, List<DeliveryModel> deliveries, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.deliveryType = deliveryType;
        this.deliveries = deliveries;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (deliveryType != null) deliveryType = deliveryType.toUpperCase(Locale.ROOT);

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        if (updatedAt != null) updatedAt = localDateTimeUtil.nowGMT3();
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
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
