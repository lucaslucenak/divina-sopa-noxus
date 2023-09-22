package com.lucalucenak.Noxus.dtos.post;

import com.lucalucenak.Noxus.models.AddressModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;


public class AddressPostDto {

    @NotNull(message = "Field streetName shouldn't be null")
    Long id;

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

    @NotNull(message = "Field neighbourhoodId shouldn't be null")
    private Long neighbourhoodId;

    @NotNull(message = "Field clientAccountId shouldn't be null")
    private Long clientAccountId;

    public AddressPostDto() {
    }

    public AddressPostDto(Long id, String streetName, String houseNumber, String city, String cep, String complement, String referencePoint, Long neighbourhoodId, Long clientAccountId) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.cep = cep;
        this.complement = complement;
        this.referencePoint = referencePoint;
        this.neighbourhoodId = neighbourhoodId;
        this.clientAccountId = clientAccountId;
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

    public Long getNeighbourhoodId() {
        return neighbourhoodId;
    }

    public void setNeighbourhoodId(Long neighbourhoodId) {
        this.neighbourhoodId = neighbourhoodId;
    }

    public Long getClientAccountId() {
        return clientAccountId;
    }

    public void setClientAccountId(Long clientAccountId) {
        this.clientAccountId = clientAccountId;
    }
}
