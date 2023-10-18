package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class NeighbourhoodFullDto {

    private Long id;

    private String neighbourhood;

    private Double deliveryTax;

    private List<AddressModel> addresses;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public NeighbourhoodFullDto() {
    }

    public NeighbourhoodFullDto(NeighbourhoodModel neighbourhoodModel) {
        BeanUtils.copyProperties(neighbourhoodModel, this);
    }

    public NeighbourhoodFullDto(Long id, String neighbourhood, Double deliveryTax, List<AddressModel> addresses, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.deliveryTax = deliveryTax;
        this.addresses = addresses;
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
