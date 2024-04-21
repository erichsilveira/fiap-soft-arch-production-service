package com.fiap.production.infrastructure.model;

import com.fiap.production.domain.entity.ProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "product_categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryModel {

    public static ProductCategoryModel toProductCategoryModel(ProductCategory productCategory) {
        return ProductCategoryModel.builder()
            .id(productCategory.getId())
            .name(productCategory.getName())
            .description(productCategory.getDescription())
            .createdAt(productCategory.getCreatedAt())
            .updatedAt(productCategory.getUpdatedAt())
            .build();
    }

    public static ProductCategory toProductCategory(ProductCategoryModel productCategoryModel) {
        return ProductCategory.builder()
            .id(productCategoryModel.getId())
            .name(productCategoryModel.getName())
            .description(productCategoryModel.getDescription())
            .createdAt(productCategoryModel.getCreatedAt())
            .updatedAt(productCategoryModel.getUpdatedAt())
            .build();
    }

    @Id
    @GeneratedValue
    @UuidGenerator
    private String id;

    private String name;

    private String description;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
