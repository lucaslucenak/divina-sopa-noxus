package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CouponPostDto {

    private Long id;

    @NotNull(message = "Field description shouldn't be null")
    @NotEmpty(message = "Field description shouldn't be empty")
    @NotBlank(message = "Field description shouldn't be blank")
    private String description;

    @NotNull(message = "Field value shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double value;

    @NotNull(message = "Field minimumOrderValue shouldn't be null")
    @DecimalMin(value = "0.0", inclusive = true)
    private Double minimumOrderValue;

    @NotNull(message = "Field price shouldn't be null")
    @DecimalMin(value = "1", inclusive = true)
    private Integer maxUsages;

    private LocalDateTime startAt;

    private LocalDateTime finishAt;

    private Long statusId;

    public CouponPostDto() {
    }

    public CouponPostDto(Long id, String description, Double value, Double minimumOrderValue, Integer maxUsages, LocalDateTime startAt, LocalDateTime finishAt, Long statusId) {
        this.id = id;
        this.description = description;
        this.value = value;
        this.minimumOrderValue = minimumOrderValue;
        this.maxUsages = maxUsages;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.statusId = statusId;
    }

    public Integer getMaxUsages() {
        return maxUsages;
    }

    public void setMaxUsages(Integer maxUsages) {
        this.maxUsages = maxUsages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(Double minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(LocalDateTime finishAt) {
        this.finishAt = finishAt;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
