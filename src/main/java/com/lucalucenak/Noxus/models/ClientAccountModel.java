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
}
