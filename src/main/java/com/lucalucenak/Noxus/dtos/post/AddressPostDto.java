package com.lucalucenak.Noxus.dtos.post;

public class AddressPostDto {

    private Long id;

    private String streetName;

    private String houseNumber;

    private String city;

    private String cep;

    private String complement;

    private String referencePoint;

    private Long neighbourhoodId;

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
