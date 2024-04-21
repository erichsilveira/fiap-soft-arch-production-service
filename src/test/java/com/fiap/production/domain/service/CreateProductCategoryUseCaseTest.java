package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.domain.repository.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductCategoryUseCaseTest {

    @Mock
    private ProductCategoryRepository repository;

    @InjectMocks
    private CreateProductCategoryUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductCategory() {
        // Dados de entrada
        ProductCategory productCategory = ProductCategory.builder()
                .id("1")
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        // Comportamento esperado
        when(repository.createProductCategory(productCategory)).thenReturn(productCategory);

        // Chamada ao método a ser testado
        ProductCategory returnedCategory = service.createProductCategory(productCategory);

        // Verificações
        assertNotNull(returnedCategory);
        assertEquals("Beverages", returnedCategory.getName());
        assertEquals("Soft drinks, coffees, teas, beers, and ales", returnedCategory.getDescription());
        verify(repository).createProductCategory(productCategory); // Verifica se o repositório foi chamado
    }
}
