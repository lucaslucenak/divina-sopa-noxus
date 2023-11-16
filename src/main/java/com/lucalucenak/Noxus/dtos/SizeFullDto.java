package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.ProductModel;
import com.lucalucenak.Noxus.models.SizeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import lombok.Builder;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class SizeFullDto {

    private Long id;

    private String size;

    private List<ProductModel> products;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SizeFullDto() {
    }

    public SizeFullDto(SizeModel sizeModel) {
        BeanUtils.copyProperties(sizeModel, this);
    }

    public SizeFullDto(Long id, String size, List<ProductModel> products, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.products = products;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
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
