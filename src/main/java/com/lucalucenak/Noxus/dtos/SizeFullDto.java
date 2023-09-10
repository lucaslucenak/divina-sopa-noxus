package com.lucalucenak.Noxus.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.SizeEnum;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.SoupModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

public class SizeFullDto {

    private Long id;

    @NotNull(message = "Field size shouldn't be null")
    @NotEmpty(message = "Field size shouldn't be empty")
    @NotBlank(message = "Field size shouldn't be blank")
    private SizeEnum size;

    @NotNull(message = "Field soups shouldn't be null")
    private List<SoupModel> soups;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public SizeFullDto() {
    }

    public SizeFullDto(SizeModel sizeModel) {
        BeanUtils.copyProperties(sizeModel, this);
    }

    public SizeFullDto(Long id, SizeEnum size, List<SoupModel> soups, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.soups = soups;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SizeEnum getSize() {
        return size;
    }

    public void setSize(SizeEnum size) {
        this.size = size;
    }

    public List<SoupModel> getSoups() {
        return soups;
    }

    public void setSoups(List<SoupModel> soups) {
        this.soups = soups;
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
