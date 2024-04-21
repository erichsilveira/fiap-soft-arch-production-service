package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.domain.repository.ProductCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchProductCategoryUseCaseTest {

    @Mock
    private ProductCategoryRepository repository;

    @InjectMocks
    private SearchProductCategoryUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchProductCategories() {
        // Dados de entrada
        ZonedDateTime now = ZonedDateTime.now();
        ProductCategory cat1 = ProductCategory.builder()
                .id("1")
                .name("Beverages")
                .description("Includes soft drinks and alcohols")
                .createdAt(now)
                .updatedAt(now)
                .build();
        ProductCategory cat2 = ProductCategory.builder()
                .id("2")
                .name("Snacks")
                .description("Includes chips and nuts")
                .createdAt(now)
                .updatedAt(now)
                .build();
        List<ProductCategory> expectedCategories = Arrays.asList(cat1, cat2);

        // Comportamento esperado
        when(repository.searchProductCategories()).thenReturn(expectedCategories);

        // Chamada ao método a ser testado
        List<ProductCategory> resultCategories = service.searchProductCategories();

        // Verificações
        assertNotNull(resultCategories);
        assertEquals(2, resultCategories.size());
        assertTrue(resultCategories.containsAll(expectedCategories));
        verify(repository).searchProductCategories(); // Verifica se o método do repositório foi chamado corretamente
    }
}
