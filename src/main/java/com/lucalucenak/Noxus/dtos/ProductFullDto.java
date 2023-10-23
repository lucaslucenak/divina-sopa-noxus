package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.dtos.response.DrinkReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductReturnDto;
import com.lucalucenak.Noxus.models.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class ProductFullDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private ProductTypeModel productType;

    private StatusModel status;

    private SizeModel size;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductFullDto() {
    }

    public ProductFullDto(ProductModel productModel) {
        BeanUtils.copyProperties(productModel, this);
    }

    public ProductFullDto(ProductReturnDto productReturnDto) {
        BeanUtils.copyProperties(productReturnDto, this);
    }

    public ProductFullDto(Long id, String name, String description, Double price, ProductTypeModel productType, StatusModel status, SizeModel size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productType = productType;
        this.status = status;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SizeModel getSize() {
        return size;
    }

    public void setSize(SizeModel size) {
        this.size = size;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductTypeModel getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeModel productType) {
        this.productType = productType;
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
