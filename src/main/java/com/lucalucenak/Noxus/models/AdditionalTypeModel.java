package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.AdditionalTypeFullDto;
import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalTypePostDto;
import com.lucalucenak.Noxus.dtos.post.ProductTypePostDto;
import com.lucalucenak.Noxus.dtos.response.AdditionalTypeReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductTypeReturnDto;
import com.lucalucenak.Noxus.enums.SelectionType;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "additional_type")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class AdditionalTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean isMandatory;

    @Column(nullable = false)
    private SelectionType selectionType;

    public AdditionalTypeModel(){
    }

    public AdditionalTypeModel(AdditionalTypeFullDto additionalTypeFullDto) {
        BeanUtils.copyProperties(additionalTypeFullDto, this);
    }

    public AdditionalTypeModel(AdditionalTypeReturnDto additionalTypeReturnDto) {
        BeanUtils.copyProperties(additionalTypeReturnDto, this);
    }


    public AdditionalTypeModel(AdditionalTypePostDto additionalTypePostDto) {
        BeanUtils.copyProperties(additionalTypePostDto, this);
    }

    public AdditionalTypeModel(Long id, String name, String description, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean isMandatory, SelectionType selectionType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isMandatory = isMandatory;
        this.selectionType = selectionType;
    }

    @PrePersist
    public void prePersist() {
        if (name != null) {
            name = name.toUpperCase(Locale.ROOT);
        }

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        if (updatedAt != null) {
            updatedAt = localDateTimeUtil.nowGMT3();
        }
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
