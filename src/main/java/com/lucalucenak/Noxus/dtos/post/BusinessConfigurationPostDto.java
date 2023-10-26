package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BusinessConfigurationPostDto {

    private Long id;

    @NotNull(message = "Field configKey shouldn't be null")
    @NotEmpty(message = "Field configKey shouldn't be empty")
    @NotBlank(message = "Field configKey shouldn't be blank")
    private String configKey;

    @NotNull(message = "Field configValue shouldn't be null")
    @NotEmpty(message = "Field configValue shouldn't be empty")
    @NotBlank(message = "Field configValue shouldn't be blank")
    private String configValue;

    @NotNull(message = "Field configDescription shouldn't be null")
    @NotEmpty(message = "Field configDescription shouldn't be empty")
    @NotBlank(message = "Field configDescription shouldn't be blank")
    private String configDescription;

    @NotNull(message = "Field configDescription shouldn't be null")
    private Long statusId;

    public BusinessConfigurationPostDto() {
    }

    public BusinessConfigurationPostDto(Long id, String configKey, String configValue, String configDescription, Long statusId) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configDescription = configDescription;
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
}
