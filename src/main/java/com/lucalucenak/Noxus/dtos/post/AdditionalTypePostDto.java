package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.enums.SelectionType;
import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AdditionalTypePostDto {

    private Long id;

    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    @NotNull(message = "Field description shouldn't be null")
    @NotEmpty(message = "Field description shouldn't be empty")
    @NotBlank(message = "Field description shouldn't be blank")
    private String description;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    @NotNull(message = "Field isMandatory shouldn't be null")
    private Boolean isMandatory;

    @NotNull(message = "Field selectionType shouldn't be null")
    private SelectionType selectionType;

    public AdditionalTypePostDto(){
    }

    public AdditionalTypePostDto(Long id, String name, String description, Long statusId, Boolean isMandatory, SelectionType selectionType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.statusId = statusId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
