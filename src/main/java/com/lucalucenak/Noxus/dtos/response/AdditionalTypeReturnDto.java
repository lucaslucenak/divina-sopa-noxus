package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AdditionalTypeFullDto;
import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.ProductTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AdditionalTypeReturnDto {

    private Long id;

    private String type;

    private String description;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public AdditionalTypeReturnDto(){
    }

    public AdditionalTypeReturnDto(AdditionalTypeModel additionalTypeModel) {
        BeanUtils.copyProperties(additionalTypeModel, this);
    }

    public AdditionalTypeReturnDto(AdditionalTypeFullDto additionalTypeFullDto) {
        BeanUtils.copyProperties(additionalTypeFullDto, this);
    }

    public AdditionalTypeReturnDto(Long id, String type, String description, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
