package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class DistanceTaxPostDto {

    private Long id;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double tax;

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

    public DistanceTaxPostDto(Long id, Double tax, Double initialDistance, Double finalDistance, Long statusId) {
        this.id = id;
        this.tax = tax;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}