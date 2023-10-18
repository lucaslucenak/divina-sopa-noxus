package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.SoupModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class SoupFullDto {

    private Long id;

    private String name;

    private Double price;

    private SizeModel size;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SoupFullDto() {
    }

    public SoupFullDto(SoupModel soupModel) {
        BeanUtils.copyProperties(soupModel, this);
    }

    public SoupFullDto(Long id, String name, Double price, SizeModel size, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
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

    public SizeModel getSize() {
        return size;
    }

    public void setSize(SizeModel size) {
        this.size = size;
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
