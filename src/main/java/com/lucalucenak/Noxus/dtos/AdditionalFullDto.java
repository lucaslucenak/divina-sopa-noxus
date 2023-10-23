package com.lucalucenak.Noxus.dtos;


import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductReturnDto;
import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.ProductModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class AdditionalFullDto {

    private Long id;

    private String name;

    private Double price;

    private String type;

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

    public AdditionalFullDto(Long id, String name, Double price, String type, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
