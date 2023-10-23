package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.models.ProductModel;
import com.lucalucenak.Noxus.models.SoupModel;

public class OrderReturnProductFieldDto {

    private ProductModel product;
    private Integer quantity;
    private Double price;

    public OrderReturnProductFieldDto() {
    }

    public OrderReturnProductFieldDto(ProductModel product, Integer quantity, Double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
