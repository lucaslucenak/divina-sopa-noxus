package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.StatusEnum;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "status")
@EntityListeners(AuditingEntityListener.class)
public class StatusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ClientAccountModel> clientAccounts;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
