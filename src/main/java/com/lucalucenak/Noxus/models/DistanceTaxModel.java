package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.DistanceTaxFullDto;
import com.lucalucenak.Noxus.dtos.post.DistanceTaxPostDto;
import com.lucalucenak.Noxus.dtos.response.DistanceTaxReturnDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "distance_tax")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DistanceTaxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tax;

    @Column(unique = true)
    private Double initialDistance;

    @Column(unique = true)
    private Double finalDistance;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public DistanceTaxModel() {
    }

    public DistanceTaxModel(DistanceTaxFullDto distanceTaxFullDto) {
        BeanUtils.copyProperties(distanceTaxFullDto, this);
    }

    public DistanceTaxModel(DistanceTaxPostDto distanceTaxPostDto) {
        BeanUtils.copyProperties(distanceTaxPostDto, this);
    }

    public DistanceTaxModel(DistanceTaxReturnDto distanceTaxReturnDto) {
        BeanUtils.copyProperties(distanceTaxReturnDto, this);
    }

    public DistanceTaxModel(Long id, Double tax, Double initialDistance, Double finalDistance, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.tax = tax;
        this.initialDistance = initialDistance;
        this.finalDistance = finalDistance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getInitialDistance() {
        return initialDistance;
    }

    public void setInitialDistance(Double initialDistance) {
        this.initialDistance = initialDistance;
    }

    public Double getFinalDistance() {
        return finalDistance;
    }

    public void setFinalDistance(Double finalDistance) {
        this.finalDistance = finalDistance;
    }
}
