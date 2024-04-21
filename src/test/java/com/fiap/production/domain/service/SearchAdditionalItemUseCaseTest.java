package com.fiap.production.domain.service;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.domain.repository.AdditionalItemRepository;
import com.fiap.production.interfaces.dto.SearchAdditionalItemCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchAdditionalItemUseCaseTest {

    @Mock
    private AdditionalItemRepository repository;

    @InjectMocks
    private SearchAdditionalItemUseCaseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchAdditionalItems() {
        // Dados de entrada
        SearchAdditionalItemCommand command = new SearchAdditionalItemCommand("cat123");
        ZonedDateTime now = ZonedDateTime.now();
        AdditionalItem item1 = AdditionalItem.builder()
                .id("1")
                .name("Sauce")
                .description("Extra hot sauce")
                .price(new BigDecimal("1.99"))
                .productCategoryId("cat123")
                .createdAt(now)
                .updatedAt(now)
                .build();
        AdditionalItem item2 = AdditionalItem.builder()
                .id("2")
                .name("Cheese")
                .description("Extra cheese")
                .price(new BigDecimal("2.50"))
                .productCategoryId("cat123")
                .createdAt(now)
                .updatedAt(now)
                .build();
        List<AdditionalItem> expectedItems = Arrays.asList(item1, item2);

        // Comportamento esperado
        when(repository.searchAdditionalItems("cat123")).thenReturn(expectedItems);

        // Chamada ao método a ser testado
        List<AdditionalItem> resultItems = service.searchAdditionalItems(command);

        // Verificações
        assertNotNull(resultItems);
        assertEquals(2, resultItems.size());
        assertTrue(resultItems.containsAll(expectedItems));
        verify(repository).searchAdditionalItems("cat123"); // Verifica se o método do repositório foi chamado corretamente
    }
}
