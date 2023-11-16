package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.DeliverymanFullDto;
import com.lucalucenak.Noxus.models.DeliverymanModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.StatusModel;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class DeliverymanReturnDto {

    private Long id;

    private String name;

    private String cellphoneNumber;

    private StatusModel status;

    private List<OrderModel> orders;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DeliverymanReturnDto() {
    }

    public DeliverymanReturnDto(DeliverymanModel deliverymanModel) {
        BeanUtils.copyProperties(deliverymanModel, this);
    }

    public DeliverymanReturnDto(DeliverymanFullDto deliverymanFullDto) {
        BeanUtils.copyProperties(deliverymanFullDto, this);
    }

    public DeliverymanReturnDto(Long id, String name, String cellphoneNumber, StatusModel status, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
        this.status = status;
        this.orders = orders;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
