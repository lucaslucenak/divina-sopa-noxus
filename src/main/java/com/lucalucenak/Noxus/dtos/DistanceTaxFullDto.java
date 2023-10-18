package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.DistanceTaxModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

public class DistanceTaxFullDto {

    private Long id;

    private Double tax;

    private Double initialDistance;

    private Double finalDistance;

    private StatusModel status;

    public DistanceTaxFullDto() {
    }

    public DistanceTaxFullDto(DistanceTaxModel distanceTaxModel) {
        BeanUtils.copyProperties(distanceTaxModel, this);
    }

    public DistanceTaxFullDto(Long id, Double tax, Double initialDistance, Double finalDistance, StatusModel status) {
        this.id = id;
        this.tax = tax;
        this.initialDistance = initialDistance;
        this.finalDistance = finalDistance;
        this.status = status;
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
