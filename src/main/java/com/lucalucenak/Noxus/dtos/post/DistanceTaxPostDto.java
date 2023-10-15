package com.lucalucenak.Noxus.dtos.post;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class DistanceTaxPostDto {

    private Long id;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double initialDistance;

    @NotNull(message = "Field finalDistance shouldn't be null")
    @DecimalMin(value = "0.1", inclusive = true)
    private Double finalDistance;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public DistanceTaxPostDto() {
    }

    public DistanceTaxPostDto(Long id, Double price, Double initialDistance, Double finalDistance, Long statusId) {
        this.id = id;
        this.price = price;
        this.initialDistance = initialDistance;
        this.finalDistance = finalDistance;
        this.statusId = statusId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
