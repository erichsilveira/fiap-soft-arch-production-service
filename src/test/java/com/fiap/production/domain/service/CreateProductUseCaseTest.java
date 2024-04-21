package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private CreateProductUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Dados de entrada
        Product inputProduct = Product.builder()
                .id("123")
                .name("Energy Drink")
                .description("High caffeine drink")
                .price(new BigDecimal("1.99"))
                .productCategoryId("bev1002")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        // Comportamento esperado
        when(repository.createProduct(inputProduct)).thenReturn(inputProduct);

        // Chamada ao método a ser testado
        Product returnedProduct = service.createProduct(inputProduct);

        // Verificações
        assertNotNull(returnedProduct);
        assertEquals("Energy Drink", returnedProduct.getName());
        assertEquals("High caffeine drink", returnedProduct.getDescription());
        assertEquals(new BigDecimal("1.99"), returnedProduct.getPrice());
        assertEquals("bev1002", returnedProduct.getProductCategoryId());
        verify(repository).createProduct(inputProduct); // Verifica se o repositório foi chamado
    }
}
