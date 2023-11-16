package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.DeliverymanFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliverymanPostDto;
import com.lucalucenak.Noxus.dtos.response.DeliverymanReturnDto;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "deliveryman")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DeliverymanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String cellphoneNumber;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryman", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DeliverymanModel() {
    }

    public DeliverymanModel(DeliverymanFullDto deliverymanFullDto) {
        BeanUtils.copyProperties(deliverymanFullDto, this);
    }

    public DeliverymanModel(DeliverymanPostDto deliverymanPostDto) {
        BeanUtils.copyProperties(deliverymanPostDto, this);
    }

    public DeliverymanModel(DeliverymanReturnDto deliverymanReturnDto) {
        BeanUtils.copyProperties(deliverymanReturnDto, this);
    }

    public DeliverymanModel(Long id, String name, String cellphoneNumber, StatusModel status, List<DeliveryModel> deliveries, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
        this.status = status;
        this.deliveries = deliveries;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (name != null) name = name.toUpperCase(Locale.ROOT);
        if (cellphoneNumber != null) cellphoneNumber = cellphoneNumber.replaceAll("[^0-9]", "");

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        if (updatedAt != null) updatedAt = localDateTimeUtil.nowGMT3();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
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
}
