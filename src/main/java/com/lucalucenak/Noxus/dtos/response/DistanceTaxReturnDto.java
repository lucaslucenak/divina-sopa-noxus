package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DistanceTaxFullDto;
import com.lucalucenak.Noxus.models.DistanceTaxModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class DistanceTaxReturnDto {

    private Long id;

    private Double tax;

    private Double initialDistance;

    private Double finalDistance;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DistanceTaxReturnDto() {
    }

    public DistanceTaxReturnDto(DistanceTaxModel distanceTaxModel) {
        BeanUtils.copyProperties(distanceTaxModel, this);
    }

    public DistanceTaxReturnDto(DistanceTaxFullDto distanceTaxFullDto) {
        BeanUtils.copyProperties(distanceTaxFullDto, this);
    }

    public DistanceTaxReturnDto(Long id, Double tax, Double initialDistance, Double finalDistance, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
