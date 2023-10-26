package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.dtos.response.AdditionalReturnDto;
import com.lucalucenak.Noxus.dtos.response.BusinessConfigurationReturnDto;
import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.BusinessConfigurationModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class BusinessConfigurationFullDto {

    private Long id;

    private String configKey;

    private String configValue;

    private String configDescription;

    private StatusModel status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BusinessConfigurationFullDto() {
    }

    public BusinessConfigurationFullDto(BusinessConfigurationModel businessConfigurationModel) {
        BeanUtils.copyProperties(businessConfigurationModel, this);
    }

    public BusinessConfigurationFullDto(BusinessConfigurationReturnDto businessConfigurationReturnDto) {
        BeanUtils.copyProperties(businessConfigurationReturnDto, this);
    }

    public BusinessConfigurationFullDto(Long id, String configKey, String configValue, String configDescription, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configDescription = configDescription;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigDescription() {
        return configDescription;
    }

    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
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
