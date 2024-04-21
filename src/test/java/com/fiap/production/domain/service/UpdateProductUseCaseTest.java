package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.interfaces.dto.UpdateProductCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private UpdateProductUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateProduct_Success() {
        // Dados de entrada
        Product existingProduct = Product.builder()
                .id("1")
                .name("Coffee")
                .description("Premium arabica coffee")
                .price(new BigDecimal("15.99"))
                .productCategoryId("coffee123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        UpdateProductCommand command = new UpdateProductCommand("1", existingProduct);

        // Comportamento esperado
        when(repository.updateProduct("1", existingProduct)).thenReturn(existingProduct);

        // Chamada ao método a ser testado
        Product updatedProduct = service.updateProduct(command);

        // Verificações
        assertNotNull(updatedProduct);
        assertEquals("Coffee", updatedProduct.getName());
        verify(repository).updateProduct("1", existingProduct);
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Dados de entrada
        Product newProduct = Product.builder()
                .id("2")
                .name("Tea")
                .description("Green tea")
                .price(new BigDecimal("10.99"))
                .productCategoryId("tea123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        UpdateProductCommand command = new UpdateProductCommand("2", newProduct);

        // Comportamento esperado
        when(repository.updateProduct("2", newProduct)).thenThrow(new ResourceNotFoundException());

        // Teste
        assertThrows(ResourceNotFoundException.class, () -> {
            service.updateProduct(command);
        });

        // Verificação
        verify(repository).updateProduct("2", newProduct);
    }
}
