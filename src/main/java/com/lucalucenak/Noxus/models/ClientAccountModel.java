package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client_account")
@EntityListeners(AuditingEntityListener.class)
public class ClientAccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer placedOrdersQuantity;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private StatusModel status;

    @JsonIgnore
    @OneToMany(mappedBy = "clientAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AddressModel> addresses;

    @JsonIgnore
    @OneToMany(mappedBy = "clientAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public ClientAccountModel() {
    }

    public ClientAccountModel(Long id, String firstName, String lastName, String cpf, String email, Integer placedOrdersQuantity, StatusModel status, List<AddressModel> addresses, List<OrderModel> orders, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.placedOrdersQuantity = placedOrdersQuantity;
        this.status = status;
        this.addresses = addresses;
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

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
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
