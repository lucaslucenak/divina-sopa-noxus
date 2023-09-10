package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_drink")
@EntityListeners(AuditingEntityListener.class)
public class OrderDrinkModel {

    @EmbeddedId
    private OrderDrinkPk id = new OrderDrinkPk();

    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
