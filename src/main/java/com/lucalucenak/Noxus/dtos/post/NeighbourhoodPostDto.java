package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NeighbourhoodPostDto {

    private Long id;

    @NotNull(message = "Field neighbourhood shouldn't be null")
    @NotEmpty(message = "Field neighbourhood shouldn't be empty")
    @NotBlank(message = "Field neighbourhood shouldn't be blank")
    private String neighbourhood;

    @NotNull(message = "Field deliveryTax shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double deliveryTax;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public NeighbourhoodPostDto() {
    }

    public NeighbourhoodPostDto(Long id, String neighbourhood, Double deliveryTax, Long statusId) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.deliveryTax = deliveryTax;
        this.statusId = statusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Double getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(Double deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
