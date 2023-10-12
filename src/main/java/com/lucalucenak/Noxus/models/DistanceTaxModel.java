package com.lucalucenak.Noxus.models;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "distance")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DistanceTaxModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @Column(unique = true)
    private Double initialDistance;

    @Column(unique = true)
    private Double finalDistance;

    public DistanceTaxModel(Long id, Double price, Double initialDistance, Double finalDistance) {
        this.id = id;
        this.price = price;
        this.initialDistance = initialDistance;
        this.finalDistance = finalDistance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInitialDistance() {
        return initialDistance;
    }

    public void setInitialDistance(Double initialDistance) {
        this.initialDistance = initialDistance;
    }

    public Double getFinalDistance() {
        return finalDistance;
    }

    public void setFinalDistance(Double finalDistance) {
        this.finalDistance = finalDistance;
    }
}
