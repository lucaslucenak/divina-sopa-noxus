package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.ProductFullDto;
import org.springframework.beans.BeanUtils;

public class AdditionalReturnDto {

    private Long id;

    private String name;

    private Double price;

    private String type;

    public AdditionalReturnDto() {
    }

    public AdditionalReturnDto(AdditionalFullDto additionalFullDto) {
        BeanUtils.copyProperties(additionalFullDto, this);
    }

    public AdditionalReturnDto(Long id, String name, Double price, String type) {
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
