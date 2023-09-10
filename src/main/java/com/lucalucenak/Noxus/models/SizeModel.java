package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.enums.SizeEnum;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "size")
@EntityListeners(AuditingEntityListener.class)
public class SizeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SizeEnum size;

    @JsonIgnore
    @OneToMany(mappedBy = "size", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SoupModel> soups;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
