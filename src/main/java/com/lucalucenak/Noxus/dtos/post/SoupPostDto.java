package com.lucalucenak.Noxus.dtos.post;

public class SoupPostDto {

    private Long id;

    private String name;

    private Double price;

    private Long sizeId;

    public SoupPostDto() {
    }

    public SoupPostDto(Long id, String name, Double price, Long sizeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sizeId = sizeId;
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

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }
}
