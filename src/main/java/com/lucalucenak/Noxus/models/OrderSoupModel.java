package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_soup")
@EntityListeners(AuditingEntityListener.class)
public class OrderSoupModel {

    @EmbeddedId
    private OrderSoupPk id = new OrderSoupPk();

    @Column(nullable = false)
    private Integer quantity;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
