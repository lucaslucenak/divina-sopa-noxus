package com.lucalucenak.Noxus.models.pks;

import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.SoupModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OrderSoupPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "soup_id")
    private SoupModel soup;
}
