package com.lucalucenak.Noxus.dtos;

import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import com.lucalucenak.Noxus.models.OrderModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class AddressFullDto {

    private Long id;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String streetName;

    @NotNull(message = "Field streetNumber shouldn't be null")
    @NotEmpty(message = "Field streetNumber shouldn't be empty")
    @NotBlank(message = "Field streetNumber shouldn't be blank")
    private String streetNumber;

    @NotNull(message = "Field city shouldn't be null")
    @NotEmpty(message = "Field city shouldn't be empty")
    @NotBlank(message = "Field city shouldn't be blank")
    private String city;

    @NotNull(message = "Field cep shouldn't be null")
    @NotEmpty(message = "Field cep shouldn't be empty")
    @NotBlank(message = "Field cep shouldn't be blank")
    private String cep;

    private String complement;

    @NotNull(message = "Field referencePoint shouldn't be null")
    @NotEmpty(message = "Field referencePoint shouldn't be empty")
    @NotBlank(message = "Field referencePoint shouldn't be blank")
    private String referencePoint;

    @NotNull(message = "Field neighbourhood shouldn't be null")
    private NeighbourhoodModel neighbourhood;

    @NotNull(message = "Field clientAccount shouldn't be null")
    private ClientAccountModel clientAccount;

    @NotNull(message = "Field orders shouldn't be null")
    private List<OrderModel> orders;

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public AddressFullDto() {
    }

    public AddressFullDto(AddressModel addressModel) {
        BeanUtils.copyProperties(addressModel, this);
    }

    public AddressFullDto(Long id, String streetName, String streetNumber, String city, String cep, String complement, String referencePoint, NeighbourhoodModel neighbourhood, ClientAccountModel clientAccount, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.cep = cep;
        this.complement = complement;
        this.referencePoint = referencePoint;
        this.neighbourhood = neighbourhood;
        this.clientAccount = clientAccount;
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

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getReferencePoint() {
        return referencePoint;
    }

    public void setReferencePoint(String referencePoint) {
        this.referencePoint = referencePoint;
    }

    public NeighbourhoodModel getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(NeighbourhoodModel neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public ClientAccountModel getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccountModel clientAccount) {
        this.clientAccount = clientAccount;
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
