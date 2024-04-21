package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.domain.repository.AdditionalItemRepository;
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

class CreateAdditionalItemUseCaseTest {

    @Mock
    private AdditionalItemRepository repository;

    @InjectMocks
    private CreateAdditionalItemUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAdditionalItem() {
        // Dados de entrada
        AdditionalItem inputItem = AdditionalItem.builder()
                .id("1")
                .name("Extra Sauce")
                .description("Delicious extra BBQ sauce")
                .price(new BigDecimal("2.50"))
                .productCategoryId("sauce123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();

        // Comportamento esperado
        when(repository.createAdditionalItem(inputItem)).thenReturn(inputItem);

        // Chamada ao método a ser testado
        AdditionalItem returnedItem = service.createAdditionalItem(inputItem);

        // Verificações
        assertNotNull(returnedItem);
        assertEquals("Extra Sauce", returnedItem.getName());
        assertEquals("Delicious extra BBQ sauce", returnedItem.getDescription());
        assertEquals(new BigDecimal("2.50"), returnedItem.getPrice());
        assertEquals("sauce123", returnedItem.getProductCategoryId());
        assertNotNull(returnedItem.getCreatedAt());
        assertNotNull(returnedItem.getUpdatedAt());
        verify(repository).createAdditionalItem(inputItem); // Verifica se o repositório foi chamado
    }
}
