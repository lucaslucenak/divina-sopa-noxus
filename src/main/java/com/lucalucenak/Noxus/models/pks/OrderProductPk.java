package com.lucalucenak.Noxus.models.pks;

import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.ProductModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.io.Serializable;

@Embeddable
@Builder
public class OrderProductPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    public OrderProductPk() {
    }

    public OrderProductPk(OrderModel order, ProductModel product) {
        this.order = order;
        this.product = product;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }
}
