package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.ProductTypeFullDto;
import com.lucalucenak.Noxus.dtos.StatusFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.post.ProductTypePostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductTypeReturnDto;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "product_type")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class ProductTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public ProductTypeModel() {
    }

    public ProductTypeModel(ProductTypeFullDto productTypeFullDto) {
        BeanUtils.copyProperties(productTypeFullDto, this);
    }

    public ProductTypeModel(ProductTypeReturnDto productTypeReturnDto) {
        BeanUtils.copyProperties(productTypeReturnDto, this);
    }


    public ProductTypeModel(ProductTypePostDto productTypePostDto) {
        BeanUtils.copyProperties(productTypePostDto, this);
    }

    public ProductTypeModel(Long id, String type, String description, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (type != null) type = type.toUpperCase(Locale.ROOT);
        if (description != null) description = description.toUpperCase(Locale.ROOT);

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        if (updatedAt != null) updatedAt = localDateTimeUtil.nowGMT3();
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
