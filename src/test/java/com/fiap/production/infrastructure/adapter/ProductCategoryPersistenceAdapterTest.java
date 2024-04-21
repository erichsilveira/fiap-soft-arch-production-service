package com.fiap.production.infrastructure.adapter;

import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import com.fiap.production.infrastructure.persistence.ProductCategoryJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductCategoryPersistenceAdapterTest {

    @Mock
    private ProductCategoryJpaRepository springDataRepository;

    @InjectMocks
    private ProductCategoryPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductCategory() {
        // Given
        ProductCategory productCategory = ProductCategory.builder()
                .id("1")
                .name("Beverages")
                .description("Drinks category")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        ProductCategoryModel model = ProductCategoryModel.toProductCategoryModel(productCategory);

        // When
        when(springDataRepository.save(any(ProductCategoryModel.class))).thenReturn(model);

        // Act
        ProductCategory createdCategory = adapter.createProductCategory(productCategory);

        // Assert
        assertNotNull(createdCategory);
        assertEquals("Beverages", createdCategory.getName());
        verify(springDataRepository).save(any(ProductCategoryModel.class));
    }

    @Test
    void testSearchProductCategories() {
        // Given
        ProductCategoryModel model = new ProductCategoryModel("1", "Beverages", "Drinks category", ZonedDateTime.now(), ZonedDateTime.now());
        List<ProductCategoryModel> models = Arrays.asList(model);

        // When
        when(springDataRepository.findAll()).thenReturn(models);

        // Act
        List<ProductCategory> categories = adapter.searchProductCategories();

        // Assert
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
        assertEquals("Beverages", categories.get(0).getName());
        verify(springDataRepository).findAll();
    }
}
