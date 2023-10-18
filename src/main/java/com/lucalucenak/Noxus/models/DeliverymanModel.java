package com.lucalucenak.Noxus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucalucenak.Noxus.dtos.DeliverymanFullDto;
import com.lucalucenak.Noxus.dtos.post.DeliverymanPostDto;
import com.lucalucenak.Noxus.dtos.response.DeliverymanReturnDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "deliveryman")
@EntityListeners(AuditingEntityListener.class)
@Builder
public class DeliverymanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Field name shouldn't be null")
    @NotEmpty(message = "Field name shouldn't be empty")
    @NotBlank(message = "Field name shouldn't be blank")
    private String name;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Field cellphoneNumber shouldn't be null")
    @NotEmpty(message = "Field cellphoneNumber shouldn't be empty")
    @NotBlank(message = "Field cellphoneNumber shouldn't be blank")
    private String cellphoneNumber;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @NotNull(message = "Field status shouldn't be null")
    private StatusModel status;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryman", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DeliveryModel> deliveries;

    public DeliverymanModel() {
    }

    public DeliverymanModel(DeliverymanFullDto deliverymanFullDto) {
        BeanUtils.copyProperties(deliverymanFullDto, this);
    }

    public DeliverymanModel(DeliverymanPostDto deliverymanPostDto) {
        BeanUtils.copyProperties(deliverymanPostDto, this);
    }

    public DeliverymanModel(DeliverymanReturnDto deliverymanReturnDto) {
        BeanUtils.copyProperties(deliverymanReturnDto, this);
    }

    public DeliverymanModel(Long id, String name, String cellphoneNumber, StatusModel status, List<DeliveryModel> deliveries) {
        this.id = id;
        this.name = name;
        this.cellphoneNumber = cellphoneNumber;
        this.status = status;
        this.deliveries = deliveries;
    }

    @PrePersist
    public void upperCaseName() {
        if (name != null) {
            name = name.toUpperCase(Locale.ROOT);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public List<DeliveryModel> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryModel> deliveries) {
        this.deliveries = deliveries;
    }
}
