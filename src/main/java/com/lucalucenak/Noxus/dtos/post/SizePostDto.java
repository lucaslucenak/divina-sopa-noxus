package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SizePostDto {

    private Long id;

    @NotNull(message = "Field size shouldn't be null")
    @NotEmpty(message = "Field size shouldn't be empty")
    @NotBlank(message = "Field size shouldn't be blank")
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
