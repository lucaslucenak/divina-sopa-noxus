package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class StatusFullDto {

    private Long id;

    private String status;

    private List<ClientAccountModel> clientAccounts;

    private List<OrderModel> orders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public StatusFullDto() {
    }

    public StatusFullDto(StatusModel statusModel) {
        BeanUtils.copyProperties(statusModel, this);
    }

    public StatusFullDto(Long id, String status, List<ClientAccountModel> clientAccounts, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.status = status;
        this.clientAccounts = clientAccounts;
        this.orders = orders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ClientAccountModel> getClientAccounts() {
        return clientAccounts;
    }

    public void setClientAccounts(List<ClientAccountModel> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
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
