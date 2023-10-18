package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.AddressFullDto;
import com.lucalucenak.Noxus.dtos.post.AddressPostDto;
import com.lucalucenak.Noxus.dtos.response.AddressReturnDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Field streetName shouldn't be null")
    @NotEmpty(message = "Field streetName shouldn't be empty")
    @NotBlank(message = "Field streetName shouldn't be blank")
    private String streetName;
    @Column(nullable = false)
    @NotNull(message = "Field houseNumber shouldn't be null")
    @NotEmpty(message = "Field houseNumber shouldn't be empty")
    @NotBlank(message = "Field houseNumber shouldn't be blank")
    private String houseNumber;
    @Column(nullable = false)
    @NotNull(message = "Field city shouldn't be null")
    @NotEmpty(message = "Field city shouldn't be empty")
    @NotBlank(message = "Field city shouldn't be blank")
    private String city;
    @Column(nullable = false)
    @NotNull(message = "Field cep shouldn't be null")
    @NotEmpty(message = "Field cep shouldn't be empty")
    @NotBlank(message = "Field cep shouldn't be blank")
    private String cep;
    @Column(nullable = true)
    private String complement;
    @Column(nullable = false)
    @NotNull(message = "Field referencePoint shouldn't be null")
    @NotEmpty(message = "Field referencePoint shouldn't be empty")
    @NotBlank(message = "Field referencePoint shouldn't be blank")
    private String referencePoint;

    @ManyToOne
    @JoinColumn(name = "neighbourhood_id", nullable = false)
    private NeighbourhoodModel neighbourhood;

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccountModel clientAccount;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public AddressModel() {
    }

    public AddressModel(AddressReturnDto addressReturnDto) {
        BeanUtils.copyProperties(addressReturnDto, this);
    }

    public AddressModel(AddressFullDto addressFullDto) {
        BeanUtils.copyProperties(addressFullDto, this);
    }

    public AddressModel(AddressPostDto addressPostDto) {
        BeanUtils.copyProperties(addressPostDto, this);
    }

    public AddressModel(Long id, String streetName, String houseNumber, String city, String cep, String complement, String referencePoint, NeighbourhoodModel neighbourhood, ClientAccountModel clientAccount, StatusModel status, List<DeliveryModel> deliveries, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.cep = cep;
        this.complement = complement;
        this.referencePoint = referencePoint;
        this.neighbourhood = neighbourhood;
        this.clientAccount = clientAccount;
        this.status = status;
        this.deliveries = deliveries;
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

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
