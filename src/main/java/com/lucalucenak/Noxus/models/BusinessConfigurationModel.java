package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.dtos.AdditionalFullDto;
import com.lucalucenak.Noxus.dtos.BusinessConfigurationFullDto;
import com.lucalucenak.Noxus.dtos.post.AdditionalPostDto;
import com.lucalucenak.Noxus.dtos.post.BusinessConfigurationPostDto;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Table(name = "business_configuration")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class BusinessConfigurationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String configKey;

    @Column(nullable = false)
    private String configValue;

    @Column(nullable = false)
    private String configDescription;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public BusinessConfigurationModel() {
    }

    public BusinessConfigurationModel(BusinessConfigurationFullDto businessConfigurationFullDto) {
        BeanUtils.copyProperties(businessConfigurationFullDto, this);
    }

    public BusinessConfigurationModel(BusinessConfigurationPostDto businessConfigurationPostDto) {
        BeanUtils.copyProperties(businessConfigurationPostDto, this);
    }

    public BusinessConfigurationModel(Long id, String configKey, String configValue, String configDescription, StatusModel status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.configDescription = configDescription;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
        if (configKey != null) configKey = configKey.toUpperCase(Locale.ROOT);
        if (configValue != null) configValue = configValue.toUpperCase(Locale.ROOT);
        if (configDescription != null) configDescription = configDescription.toUpperCase(Locale.ROOT);

        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
        if (updatedAt != null) updatedAt = localDateTimeUtil.nowGMT3();
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
