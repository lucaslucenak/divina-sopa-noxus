package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.StatusFullDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Field status shouldn't be null")
    @NotEmpty(message = "Field status shouldn't be empty")
    @NotBlank(message = "Field status shouldn't be blank")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClientAccountModel> clientAccounts;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public StatusModel() {
    }

    public StatusModel(StatusFullDto statusFullDto) {
        BeanUtils.copyProperties(statusFullDto, this);
    }

    public StatusModel(Long id, String status, List<ClientAccountModel> clientAccounts, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
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
