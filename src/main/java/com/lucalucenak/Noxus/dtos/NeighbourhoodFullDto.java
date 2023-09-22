package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class NeighbourhoodFullDto {

    private Long id;

    @NotNull(message = "Field neighbourhood shouldn't be null")
    @NotEmpty(message = "Field neighbourhood shouldn't be empty")
    @NotBlank(message = "Field neighbourhood shouldn't be blank")
    private String neighbourhood;

    @NotNull(message = "Field deliveryTax shouldn't be null")
    private Double deliveryTax;

    @NotNull(message = "Field addresses shouldn't be null")
    private List<AddressModel> addresses;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;


    public NeighbourhoodFullDto() {
    }

    public NeighbourhoodFullDto(NeighbourhoodModel neighbourhoodModel) {
        BeanUtils.copyProperties(neighbourhoodModel, this);
    }

    public NeighbourhoodFullDto(Long id, String neighbourhood, Double deliveryTax, List<AddressModel> addresses, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.deliveryTax = deliveryTax;
        this.addresses = addresses;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public Double getDeliveryTax() {
        return deliveryTax;
    }

    public void setDeliveryTax(Double deliveryTax) {
        this.deliveryTax = deliveryTax;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
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
