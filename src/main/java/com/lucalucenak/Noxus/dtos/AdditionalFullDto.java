package com.lucalucenak.Noxus.dtos;


import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductReturnDto;
import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.ProductModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class AdditionalFullDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private AdditionalTypeModel additionalType;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AdditionalFullDto() {
    }

    public AdditionalFullDto(AdditionalModel additionalModel) {
        BeanUtils.copyProperties(additionalModel, this);
    }

    public AdditionalFullDto(AdditionalReturnDto additionalReturnDto) {
        BeanUtils.copyProperties(additionalReturnDto, this);
    }

    public AdditionalFullDto(Long id, String name, String description, Double price, AdditionalTypeModel additionalType, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.additionalType = additionalType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public AdditionalTypeModel getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(AdditionalTypeModel additionalType) {
        this.additionalType = additionalType;
    }
}
