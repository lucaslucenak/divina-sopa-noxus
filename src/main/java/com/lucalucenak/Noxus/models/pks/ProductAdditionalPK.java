package com.lucalucenak.Noxus.models.pks;

import com.lucalucenak.Noxus.models.AdditionalModel;
import com.lucalucenak.Noxus.models.ProductModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ProductAdditionalPK implements Serializable {

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name="additional_id")
    private AdditionalModel additional;

    public ProductAdditionalPK() {};

    public ProductAdditionalPK(ProductModel product, AdditionalModel additional) {
        this.product = product;
        this.additional = additional;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public AdditionalModel getAdditional() {
        return additional;
    }

    public void setAdditional(AdditionalModel additional) {
        this.additional = additional;
    }

    @Override
    public String toString() {
        return "ProductAdditionalPK{" +
                "product=" + product +
                ", additional=" + additional +
                '}';
    }
}
