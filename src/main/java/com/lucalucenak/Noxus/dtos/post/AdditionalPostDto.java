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

    @NotNull(message = "Field type shouldn't be null")
    @NotEmpty(message = "Field type shouldn't be empty")
    @NotBlank(message = "Field type shouldn't be blank")
    private String type;

    private Long statusId;

    public AdditionalPostDto() {
    }

    public AdditionalPostDto(Long id, String name, String description, Double price, String type, Long statusId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
