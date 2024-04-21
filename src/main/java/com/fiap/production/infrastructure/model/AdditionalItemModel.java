package com.fiap.production.infrastructure.model;

import com.fiap.production.domain.entity.AdditionalItem;
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
@Table(name = "additional_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalItemModel {

    public static AdditionalItemModel toAdditionalItemModel(AdditionalItem additionalItem) {
        return AdditionalItemModel.builder()
                .id(additionalItem.getId())
                .name(additionalItem.getName())
                .description(additionalItem.getDescription())
                .price(additionalItem.getPrice())
                .productCategory(ProductCategoryModel.builder().id(additionalItem.getProductCategoryId()).build())
                .createdAt(additionalItem.getCreatedAt())
                .updatedAt(additionalItem.getUpdatedAt())
                .build();
    }

    public static AdditionalItem toAdditionalItem(AdditionalItemModel additionalItemModel) {
        if (additionalItemModel == null) {
            return null;
        }

        return AdditionalItem.builder()
                .id(additionalItemModel.getId())
                .name(additionalItemModel.getName())
                .description(additionalItemModel.getDescription())
                .price(additionalItemModel.getPrice())
                .productCategoryId(additionalItemModel.getProductCategory() != null ? additionalItemModel.getProductCategory().getId() : null)
                .createdAt(additionalItemModel.getCreatedAt())
                .updatedAt(additionalItemModel.getUpdatedAt())
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
