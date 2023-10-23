package com.lucalucenak.Noxus.dtos.post;

public class OrderProductPostDto {
    private Long productId;
    private Integer quantity;
    private String additions;

    public OrderProductPostDto() {
    }

    public OrderProductPostDto(Long productId, Integer quantity, String additions) {
        this.productId = productId;
        this.quantity = quantity;
        this.additions = additions;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAdditions() {
        return additions;
    }

    public void setAdditions(String additions) {
        this.additions = additions;
    }
}
