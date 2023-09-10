package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.DrinkModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class DrinkFullDto {

    private Long id;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String name;

    @NotNull(message = "Field streetName shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field streetName shouldn't be null")
    private LocalDateTime updatedAt;

    public DrinkFullDto() {
    }

    public DrinkFullDto(DrinkModel drinkModel) {
        BeanUtils.copyProperties(drinkModel, this);
    }

    public DrinkFullDto(Long id, String name, Double price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
