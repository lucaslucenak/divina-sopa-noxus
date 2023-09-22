package com.lucalucenak.Noxus.dtos.post;

public class SizePostDto {

    private Long id;

    private String size;

    public SizePostDto() {
    }

    public SizePostDto(Long id, String size) {
        this.id = id;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
