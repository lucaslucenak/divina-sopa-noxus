package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AdditionalPostDto {
    private Long id;

    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    private String description;

    @NotNull(message = "Field price shouldn't be null")
    private Double price;

    @NotNull(message = "Field limitValue shouldn't be null")
    private Integer limitValue;

    @NotNull(message = "Field additionalTypeId shouldn't be null")
    private Long additionalTypeId;

    private Long statusId;

    public AdditionalPostDto() {
    }

    public AdditionalPostDto(Long id, String name, String description, Double price, Integer limitValue, Long additionalTypeId, Long statusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.limitValue = limitValue;
        this.additionalTypeId = additionalTypeId;
        this.statusId = statusId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(Integer limitValue) {
        this.limitValue = limitValue;
    }

    public Long getAdditionalTypeId() {
        return additionalTypeId;
    }

    public void setAdditionalTypeId(Long additionalTypeId) {
        this.additionalTypeId = additionalTypeId;
    }
}
