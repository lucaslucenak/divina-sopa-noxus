package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NeighbourhoodPostDto {

    @NotNull(message = "Field neighbourhood shouldn't be null")
    Long id;

    @NotNull(message = "Field neighbourhood shouldn't be null")
    @NotEmpty(message = "Field neighbourhood shouldn't be empty")
    @NotBlank(message = "Field neighbourhood shouldn't be blank")
    private String neighbourhood;

    @NotNull(message = "Field deliveryTax shouldn't be null")
    private Double deliveryTax;

    public NeighbourhoodPostDto() {
    }

    public NeighbourhoodPostDto(Long id, String neighbourhood, Double deliveryTax) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.deliveryTax = deliveryTax;
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
}
