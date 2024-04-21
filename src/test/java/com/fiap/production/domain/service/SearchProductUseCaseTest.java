package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.Product;
import com.fiap.production.domain.repository.ProductRepository;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.interfaces.dto.SearchProductByIdCommand;
import com.fiap.production.interfaces.dto.SearchProductCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private SearchProductUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchProducts() {
        // Dados de entrada
        SearchProductCommand command = new SearchProductCommand("cat123");
        Product product1 = Product.builder()
                .id("1")
                .name("Energy Drink")
                .description("Boosts energy")
                .price(new BigDecimal("1.99"))
                .productCategoryId("cat123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        List<Product> expectedProducts = Arrays.asList(product1);

        // Comportamento esperado
        when(repository.searchProducts("cat123")).thenReturn(expectedProducts);

        // Chamada ao método a ser testado
        List<Product> resultProducts = service.searchProducts(command);

        // Verificações
        assertNotNull(resultProducts);
        assertEquals(1, resultProducts.size());
        assertTrue(resultProducts.containsAll(expectedProducts));
        verify(repository).searchProducts("cat123");
    }

    @Test
    void testSearchProductById_Success() {
        // Dados de entrada
        SearchProductByIdCommand command = new SearchProductByIdCommand("1");
        Product expectedProduct = Product.builder()
                .id("1")
                .name("Energy Drink")
                .description("Boosts energy")
                .price(new BigDecimal("1.99"))
                .productCategoryId("cat123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        // Comportamento esperado
        when(repository.getProduct("1")).thenReturn(Optional.of(expectedProduct));

        // Chamada ao método a ser testado
        Product resultProduct = service.searchProductById(command);

        // Verificações
        assertNotNull(resultProduct);
        assertEquals(expectedProduct, resultProduct);
        verify(repository).getProduct("1");
    }

    @Test
    void testSearchProductById_NotFound() {
        // Dados de entrada
        SearchProductByIdCommand command = new SearchProductByIdCommand("2");

        // Comportamento esperado
        when(repository.getProduct("2")).thenReturn(Optional.empty());

        // Teste
        assertThrows(ResourceNotFoundException.class, () -> {
            service.searchProductById(command);
        });

        // Verificação
        verify(repository).getProduct("2");
    }
}
