package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String streetName;
    @Column(nullable = false)
    private String streetNumber;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = true)
    private String complement;
    @Column(nullable = false)
    private String referencePoint;

    @ManyToOne
    @JoinColumn(name = "neighbourhood_id", nullable = false)
    private NeighbourhoodModel neighbourhood;

    @ManyToOne
    @JoinColumn(name = "client_account_id", nullable = false)
    private ClientAccountModel clientAccount;

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public AddressModel() {
    }
}
