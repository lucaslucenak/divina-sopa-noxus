package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class DeliverymanPostDto {

    @NotNull(message = "Field id shouldn't be null")
    private Long id;

    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    @NotNull(message = "Field cellphoneNumber shouldn't be null")
    @NotEmpty(message = "Field cellphoneNumber shouldn't be empty")
    @NotBlank(message = "Field cellphoneNumber shouldn't be blank")
    private String cellphoneNumber;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public DeliverymanPostDto() {
    }

    public DeliverymanPostDto(Long id, String name, String cellphoneNumber, Long statusId) {
        this.id = id;
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
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

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
