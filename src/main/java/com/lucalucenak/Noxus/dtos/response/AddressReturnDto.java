package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.models.AddressModel;
import com.lucalucenak.Noxus.models.ClientAccountModel;
import com.lucalucenak.Noxus.models.NeighbourhoodModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class AddressReturnDto {

    private Long id;

    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String streetName;

    @NotNull(message = "Field houseNumber shouldn't be null")
    @NotEmpty(message = "Field houseNumber shouldn't be empty")
    @NotBlank(message = "Field houseNumber shouldn't be blank")
    private String houseNumber;

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

    @NotNull(message = "Field createdAt shouldn't be null")
    private LocalDateTime createdAt;

    @NotNull(message = "Field updatedAt shouldn't be null")
    private LocalDateTime updatedAt;

    public AddressReturnDto() {
    }

    public AddressReturnDto(AddressModel addressModel) {
        BeanUtils.copyProperties(addressModel, this);
    }

    public AddressReturnDto(AddressFullDto addressFullDto) {
        BeanUtils.copyProperties(addressFullDto, this);
    }

    public AddressReturnDto(Long id, String streetName, String houseNumber, String city, String cep, String complement, String referencePoint, NeighbourhoodModel neighbourhood, ClientAccountModel clientAccount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.cep = cep;
        this.complement = complement;
        this.referencePoint = referencePoint;
        this.neighbourhood = neighbourhood;
        this.clientAccount = clientAccount;
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String streetNumber) {
        this.houseNumber = streetNumber;
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
