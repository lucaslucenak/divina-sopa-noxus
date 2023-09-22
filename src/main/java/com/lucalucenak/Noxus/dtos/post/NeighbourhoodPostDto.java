package com.lucalucenak.Noxus.dtos.post;

public class NeighbourhoodPostDto {

    private Long id;

    private String neighbourhood;

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
