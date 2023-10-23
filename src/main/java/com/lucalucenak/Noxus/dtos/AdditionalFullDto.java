package com.lucalucenak.Noxus.dtos;


import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.dtos.response.ProductReturnDto;
import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.ProductModel;
import org.springframework.beans.BeanUtils;

public class AdditionalFullDto {

    private Long id;

    private String name;

    private Double price;

    private String type;

    public AdditionalFullDto() {
    }

    public AdditionalFullDto(AdditionalModel additionalModel) {
        BeanUtils.copyProperties(additionalModel, this);
    }

    public AdditionalFullDto(AdditionalReturnDto additionalReturnDto) {
        BeanUtils.copyProperties(additionalReturnDto, this);
    }

    public AdditionalFullDto(Long id, String name, Double price, String type) {
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
