package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.ProductFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.post.ProductPostDto;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "additional")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class AdditionalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String type;

    public AdditionalModel() {
    }

    public AdditionalModel(AdditionalFullDto additionalFullDto) {
        BeanUtils.copyProperties(additionalFullDto, this);
    }

    public AdditionalModel(AdditionalPostDto additionalPostDto) {
        BeanUtils.copyProperties(additionalPostDto, this);
    }

    public AdditionalModel(Long id, String name, Double price, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
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
