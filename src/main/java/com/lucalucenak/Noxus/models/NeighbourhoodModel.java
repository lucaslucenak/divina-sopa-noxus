package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.NeighbourhoodEnum;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private NeighbourhoodEnum neighbourhood;

    @Column(nullable = false)
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

    public NeighbourhoodModel(Long id, NeighbourhoodEnum neighbourhood, Double deliveryTax, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.neighbourhood = neighbourhood;
        this.deliveryTax = deliveryTax;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NeighbourhoodEnum getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(NeighbourhoodEnum neighbourhood) {
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
