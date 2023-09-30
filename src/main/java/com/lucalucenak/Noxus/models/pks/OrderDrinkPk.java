package com.lucalucenak.Noxus.models.pks;

import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.SoupModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.io.Serializable;

@Embeddable
@Builder
public class OrderDrinkPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "drink_id")
    private DrinkModel drink;

    public OrderDrinkPk() {
    }

    public OrderDrinkPk(OrderModel order, DrinkModel drink) {
        this.order = order;
        this.drink = drink;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public DrinkModel getDrink() {
        return drink;
    }

    public void setDrink(DrinkModel drink) {
        this.drink = drink;
    }
}
