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

    public OrderSoupPk() {
    }

    public OrderSoupPk(OrderModel order, SoupModel soup) {
        this.order = order;
        this.soup = soup;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public SoupModel getSoup() {
        return soup;
    }

    public void setSoup(SoupModel soup) {
        this.soup = soup;
    }
}
