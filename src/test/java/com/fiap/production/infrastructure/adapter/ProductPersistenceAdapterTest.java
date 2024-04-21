package com.fiap.production.infrastructure.adapter;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import com.fiap.production.infrastructure.model.ProductModel;
import com.fiap.production.infrastructure.persistence.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductPersistenceAdapterTest {

    @Mock
    private ProductJpaRepository springDataRepository;

    @InjectMocks
    private ProductPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchProducts_NoFilter() {
        // Setup
        ProductModel productModel = new ProductModel("1", "Coffee", "Rich in flavor", new BigDecimal("1.99"), null, null, null);
        List<ProductModel> models = List.of(productModel);

        // Expectation
        when(springDataRepository.findAll(any(Example.class), any(Sort.class))).thenReturn(models);

        // Act
        List<Product> products = adapter.searchProducts(null);  // Testing with no filter

        // Assert
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals("Coffee", products.get(0).getName());
    }

    @Test
    void testSearchProducts_WithFilter() {
        // Setup
        String productCategoryId = "123";
        ProductCategoryModel productCategoryModel = new ProductCategoryModel();
        productCategoryModel.setId(productCategoryId);
        ProductModel productModel = new ProductModel("1", "Coffee", "Rich in flavor", new BigDecimal("1.99"), productCategoryModel, ZonedDateTime.now(), ZonedDateTime.now());
        List<ProductModel> models = List.of(productModel);

        // Expectation
        when(springDataRepository.findAll(any(Example.class), any(Sort.class))).thenReturn(models);

        // Act
        List<Product> products = adapter.searchProducts(productCategoryId);  // Testing with category filter

        // Assert
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals("Coffee", products.get(0).getName());
        assertEquals(productCategoryId, products.get(0).getProductCategoryId());
    }


    @Test
    void testGetProduct_NotFound() {
        // Expectation
        when(springDataRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            adapter.getProduct("1").orElseThrow(ResourceNotFoundException::new);
        });
    }

    @Test
    void testUpdateProduct_Found_And_Update() throws ResourceNotFoundException {
        // Setup
        ProductModel existingModel = new ProductModel("1", "Tea", "Green tea", new BigDecimal("2.50"), null, ZonedDateTime.now(), ZonedDateTime.now());
        Product updatedProduct = new Product("1", "Tea", "Updated description", new BigDecimal("2.50"), null, ZonedDateTime.now(), ZonedDateTime.now());

        when(springDataRepository.findById("1")).thenReturn(Optional.of(existingModel));
        when(springDataRepository.save(any(ProductModel.class))).thenReturn(existingModel);

        // Act
        Product result = adapter.updateProduct("1", updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated description", result.getDescription());
        verify(springDataRepository).save(existingModel);
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Setup: Assume the product does not exist in the database
        String nonExistentId = "999";
        Product updatedProduct = new Product("999", "Coffee", "New Aroma", new BigDecimal("15.99"), "123", ZonedDateTime.now(), ZonedDateTime.now());

        when(springDataRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert: Expect ResourceNotFoundException to be thrown when the product is not found
        assertThrows(ResourceNotFoundException.class, () -> {
            adapter.updateProduct(nonExistentId, updatedProduct);
        });

        // Verify that save was never called since the product was not found
        verify(springDataRepository, never()).save(any(ProductModel.class));
    }

    @Test
    void testDeleteProduct() {
        // Setup
        String productId = "1";

        // No specific expectation needed, just need to verify action

        // Act
        adapter.deleteProduct(productId);

        // Assert
        verify(springDataRepository).deleteById(productId);
    }

}
