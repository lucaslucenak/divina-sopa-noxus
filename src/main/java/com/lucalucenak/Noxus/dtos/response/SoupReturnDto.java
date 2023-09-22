package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.dtos.SoupFullDto;
import com.lucalucenak.Noxus.models.SizeModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class SoupReturnDto {

    private Long id;

    private String name;

    private Double price;

    private SizeModel size;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SoupReturnDto() {
    }

    public SoupReturnDto(SoupFullDto soupFullDto) {
        BeanUtils.copyProperties(soupFullDto, this);
    }

    public SoupReturnDto(Long id, String name, Double price, SizeModel size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
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
