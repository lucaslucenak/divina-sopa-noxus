package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AdditionalTypeFullDto;
import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.enums.SelectionType;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.ProductTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AdditionalTypeReturnDto {

    private Long id;

    private String name;

    private String description;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean isMandatory;

    private SelectionType selectionType;

    public AdditionalTypeReturnDto(){
    }

    public AdditionalTypeReturnDto(AdditionalTypeModel additionalTypeModel) {
        BeanUtils.copyProperties(additionalTypeModel, this);
    }

    public AdditionalTypeReturnDto(AdditionalTypeFullDto additionalTypeFullDto) {
        BeanUtils.copyProperties(additionalTypeFullDto, this);
    }

    public AdditionalTypeReturnDto(Long id, String name, String description, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isMandatory, SelectionType selectionType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isMandatory = isMandatory;
        this.selectionType = selectionType;
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

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public SelectionType getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(SelectionType selectionType) {
        this.selectionType = selectionType;
    }
}
