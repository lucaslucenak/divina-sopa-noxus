package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.ProductTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductPostDto {

    private Long id;

    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    @NotNull(message = "Field description shouldn't be null")
    @NotEmpty(message = "Field description shouldn't be empty")
    @NotBlank(message = "Field description shouldn't be blank")
    private String description;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double price;

    private String imageUrl;

    @NotNull(message = "Field productTypeId shouldn't be null")
    private Long sizeId;

    @NotNull(message = "Field productTypeId shouldn't be null")
    private Long productTypeId;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public ProductPostDto() {
    }

    public ProductPostDto(Long id, String name, String description, Double price, String imageUrl, Long sizeId, Long productTypeId, Long statusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.sizeId = sizeId;
        this.productTypeId = productTypeId;
        this.statusId = statusId;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
