package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.ClientAccountFullDto;
import com.lucalucenak.Noxus.enums.RoleEnum;
import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.StatusModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class ClientAccountReturnDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private String email;

    private String cellphoneNumber;

    private Integer placedOrdersQuantity;

    private StatusModel status;

    private RoleEnum role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ClientAccountReturnDto() {
    }

    public ClientAccountReturnDto(ClientAccountModel clientAccountModel) {
        BeanUtils.copyProperties(clientAccountModel, this);
    }

    public ClientAccountReturnDto(ClientAccountFullDto clientAccountFullDto) {
        BeanUtils.copyProperties(clientAccountFullDto, this);
    }

    public ClientAccountReturnDto(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, Integer placedOrdersQuantity, StatusModel status, RoleEnum role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.placedOrdersQuantity = placedOrdersQuantity;
        this.status = status;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPlacedOrdersQuantity() {
        return placedOrdersQuantity;
    }

    public void setPlacedOrdersQuantity(Integer placedOrdersQuantity) {
        this.placedOrdersQuantity = placedOrdersQuantity;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
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
