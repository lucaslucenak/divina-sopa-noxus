package com.lucalucenak.Noxus.models.pks;

import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.SoupModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class OrderDrinkPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private DrinkModel drink;
}
