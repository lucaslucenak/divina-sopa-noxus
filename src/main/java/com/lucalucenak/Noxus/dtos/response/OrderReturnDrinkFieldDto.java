package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.models.DrinkModel;

public class OrderReturnDrinkFieldDto {

    private DrinkModel drink;
    private Integer quantity;
    private Double price;

    public OrderReturnDrinkFieldDto() {
    }

    public OrderReturnDrinkFieldDto(DrinkModel drink, Integer quantity, Double price) {
        this.drink = drink;
        this.quantity = quantity;
        this.price = price;
    }

    public DrinkModel getDrink() {
        return drink;
    }

    public void setDrink(DrinkModel drink) {
        this.drink = drink;
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
