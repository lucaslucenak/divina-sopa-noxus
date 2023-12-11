package com.lucalucenak.Noxus.models;

import com.lucalucenak.Noxus.models.pks.ProductAdditionalPK;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_additional")
@EntityListeners(AuditingEntityListener.class)
public class ProductAdditionalModel {

    @EmbeddedId
    private ProductAdditionalPK id = new ProductAdditionalPK();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public ProductAdditionalModel() {};

    public ProductAdditionalModel(ProductModel product, AdditionalModel additional, LocalDateTime createdAt, LocalDateTime updatedAt) {
        id.setProduct(product);
        id.setAdditional(additional);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductModel getProduct() {
        return id.getProduct();
    }

    public void setProductModel (ProductModel product) {
        id.setProduct(product);
    }

    public AdditionalModel getAdditional() {
        return id.getAdditional();
    }

    public void setAdditionalModel (AdditionalModel additional) {
        id.setAdditional(additional);
    }

    public void setId(ProductAdditionalPK id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
