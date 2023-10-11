package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.StatusModel;
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

    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;

    @NotNull(message = "Field status shouldn't be null")
    private StatusModel status;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public DrinkFullDto() {
    }

    public DrinkFullDto(DrinkModel drinkModel) {
        BeanUtils.copyProperties(drinkModel, this);
    }

    public DrinkFullDto(DrinkReturnDto drinkReturnDto) {
        BeanUtils.copyProperties(drinkReturnDto, this);
    }

    public DrinkFullDto(Long id, String name, Double price, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
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

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
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
