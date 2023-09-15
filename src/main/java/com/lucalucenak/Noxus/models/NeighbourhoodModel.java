package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.NeighbourhoodFullDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "neighbourhood")
@EntityListeners(AuditingEntityListener.class)
public class NeighbourhoodModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Field neighbourhood shouldn't be null")
    @NotEmpty(message = "Field neighbourhood shouldn't be empty")
    @NotBlank(message = "Field neighbourhood shouldn't be blank")
    private String neighbourhood;

    @Column(nullable = false)
    @NotNull(message = "Field deliveryTax shouldn't be null")
    @NotEmpty(message = "Field deliveryTax shouldn't be empty")
    @NotBlank(message = "Field deliveryTax shouldn't be blank")
    private Double deliveryTax;

    @JsonIgnore
    @OneToMany(mappedBy = "neighbourhood", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AddressModel> addresses;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public NeighbourhoodModel() {
    }

    public NeighbourhoodModel(NeighbourhoodFullDto neighbourhoodFullDto) {
        BeanUtils.copyProperties(neighbourhoodFullDto, this);
    }

    public NeighbourhoodModel(Long id, String neighbourhood, Double deliveryTax, List<AddressModel> addresses, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
