package com.fiap.production.infrastructure.model;

import com.fiap.production.domain.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    public static ProductModel toProductModel(Product product) {
        return ProductModel.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .productCategory(ProductCategoryModel.builder().id(product.getProductCategoryId()).build())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }

    public static Product toProduct(ProductModel productModel) {
        return Product.builder()
            .id(productModel.getId())
            .name(productModel.getName())
            .description(productModel.getDescription())
            .price(productModel.getPrice())
            .productCategoryId(productModel.getProductCategory().getId())
            .createdAt(productModel.getCreatedAt())
            .updatedAt(productModel.getUpdatedAt())
            .build();
    }

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private ProductCategoryModel productCategory;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
