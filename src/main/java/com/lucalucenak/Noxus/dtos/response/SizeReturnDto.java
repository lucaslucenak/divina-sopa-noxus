package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.SizeFullDto;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.SizeModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class SizeReturnDto {

    private Long id;

    private String size;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public SizeReturnDto() {
    }

    public SizeReturnDto(SizeModel sizeModel) {
        BeanUtils.copyProperties(sizeModel, this);
    }

    public SizeReturnDto(SizeFullDto sizeFullDto) {
        BeanUtils.copyProperties(sizeFullDto, this);
    }

    public SizeReturnDto(Long id, String size, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
