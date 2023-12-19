package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.OrderProductModel;
import com.lucalucenak.Noxus.models.ProductAdditionalModel;
import com.lucalucenak.Noxus.models.pks.ProductAdditionalPK;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class ProductAdditionalFullDto {

    private ProductAdditionalPK id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductAdditionalFullDto() {
    }

    public ProductAdditionalFullDto(ProductAdditionalModel productAdditionalModel) {
        BeanUtils.copyProperties(productAdditionalModel, this);
    }

    public ProductAdditionalFullDto(ProductAdditionalPK id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductAdditionalPK getId() {
        return id;
    }

    public void setId(ProductAdditionalPK id) {
        this.id = id;
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
