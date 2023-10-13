package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DistanceTaxFullDto;
import com.lucalucenak.Noxus.models.DistanceTaxModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

public class DistanceTaxReturnDto {

    private Long id;

    private Double price;

    private Double initialDistance;

    private Double finalDistance;

    private StatusModel status;

    public DistanceTaxReturnDto() {
    }

    public DistanceTaxReturnDto(DistanceTaxModel distanceTaxModel) {
        BeanUtils.copyProperties(distanceTaxModel, this);
    }

    public DistanceTaxReturnDto(DistanceTaxFullDto distanceTaxFullDto) {
        BeanUtils.copyProperties(distanceTaxFullDto, this);
    }

    public DistanceTaxReturnDto(Long id, Double price, Double initialDistance, Double finalDistance, StatusModel status) {
        this.id = id;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
