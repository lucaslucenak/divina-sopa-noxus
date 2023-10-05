package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.models.SoupModel;

public class OrderReturnSoupFieldDto {

    private SoupModel soup;
    private Integer quantity;
    private Double price;

    public OrderReturnSoupFieldDto() {
    }

    public OrderReturnSoupFieldDto(SoupModel soup, Integer quantity, Double price) {
        this.soup = soup;
        this.quantity = quantity;
        this.price = price;
    }

    public SoupModel getSoup() {
        return soup;
    }

    public void setSoup(SoupModel soup) {
        this.soup = soup;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
