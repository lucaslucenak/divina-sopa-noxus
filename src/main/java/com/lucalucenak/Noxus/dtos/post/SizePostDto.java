package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class SizePostDto {

    private Long id;

    @NotNull(message = "Field size shouldn't be null")
    @NotEmpty(message = "Field size shouldn't be empty")
    @NotBlank(message = "Field size shouldn't be blank")
    private String size;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public SizePostDto() {
    }

    public SizePostDto(Long id, String size, Long statusId) {
        this.id = id;
        this.size = size;
        this.statusId = statusId;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
