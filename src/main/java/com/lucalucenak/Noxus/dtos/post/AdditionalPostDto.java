package com.lucalucenak.Noxus.dtos.post;

public class AdditionalPostDto {
    private Long id;

    private String name;

    private Double price;

    private String type;

    private Long statusId;

    public AdditionalPostDto() {
    }

    public AdditionalPostDto(Long id, String name, Double price, String type, Long statusId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.statusId = statusId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
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
