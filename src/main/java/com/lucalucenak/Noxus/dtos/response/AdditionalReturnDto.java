package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.ProductFullDto;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class AdditionalReturnDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer limitValue;

    private AdditionalTypeModel additionalType;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AdditionalReturnDto() {
    }

    public AdditionalReturnDto(AdditionalFullDto additionalFullDto) {
        BeanUtils.copyProperties(additionalFullDto, this);
    }

    public AdditionalReturnDto(Long id, String name, String description, Double price, Integer limitValue, AdditionalTypeModel additionalType, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.limitValue = limitValue;
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

    public Integer getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Integer limitValue) {
        this.limitValue = limitValue;
    }

    public AdditionalTypeModel getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(AdditionalTypeModel additionalType) {
        this.additionalType = additionalType;
    }
}
