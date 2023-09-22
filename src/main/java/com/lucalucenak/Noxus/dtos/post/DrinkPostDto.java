package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DrinkPostDto {

    @NotNull(message = "Field firstName shouldn't be null")
    Long id;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String name;

    @NotNull(message = "Field streetName shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;

    public DrinkPostDto() {
    }

    public DrinkPostDto(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
