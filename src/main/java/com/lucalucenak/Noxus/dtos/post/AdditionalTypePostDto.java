package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.AdditionalTypeModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AdditionalTypePostDto {

    private Long id;

    @NotNull(message = "Field type shouldn't be null")
    @NotEmpty(message = "Field type shouldn't be empty")
    @NotBlank(message = "Field type shouldn't be blank")
    private String type;

    @NotNull(message = "Field description shouldn't be null")
    @NotEmpty(message = "Field description shouldn't be empty")
    @NotBlank(message = "Field description shouldn't be blank")
    private String description;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public AdditionalTypePostDto(){
    }

    public AdditionalTypePostDto(Long id, String type, String description, Long statusId) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.statusId = statusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

}
