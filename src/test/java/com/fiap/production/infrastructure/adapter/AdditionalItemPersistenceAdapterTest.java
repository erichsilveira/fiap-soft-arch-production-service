package com.fiap.production.infrastructure.adapter;

import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.infrastructure.model.AdditionalItemModel;
import com.fiap.production.infrastructure.model.ProductCategoryModel;
import com.fiap.production.infrastructure.persistence.AdditionalItemJpaRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdditionalItemPersistenceAdapterTest {

    @Mock
    private AdditionalItemJpaRepository springDataRepository;

    @InjectMocks
    private AdditionalItemPersistenceAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAdditionalItem() {
        // Dados de entrada
        AdditionalItem additionalItem = AdditionalItem.builder()
                .id("1")
                .name("Sauce")
                .description("Spicy sauce")
                .price(new BigDecimal("2.50"))
                .productCategoryId("sauce")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        AdditionalItemModel model = AdditionalItemModel.toAdditionalItemModel(additionalItem);

        // Comportamento esperado
        when(springDataRepository.save(any(AdditionalItemModel.class))).thenReturn(model);

        // Chamada ao método a ser testado
        AdditionalItem result = adapter.createAdditionalItem(additionalItem);

        // Verificações
        assertNotNull(result);
        assertEquals("Sauce", result.getName());
        verify(springDataRepository).save(any(AdditionalItemModel.class));
    }

    @Test
    void testSearchAdditionalItems_WithFilter() {
        // Setup do filtro
        String productCategoryId = "sauce";
        ProductCategoryModel categoryModel = ProductCategoryModel.builder().id(productCategoryId).build();
        AdditionalItemModel filterModel = AdditionalItemModel.builder()
                .productCategory(categoryModel)
                .build();
        AdditionalItemModel foundModel = new AdditionalItemModel("1", "Sauce", "Spicy", new BigDecimal("2.50"), categoryModel, ZonedDateTime.now(), ZonedDateTime.now());
        List<AdditionalItemModel> models = List.of(foundModel);

        // Comportamento esperado
        when(springDataRepository.findAll(Example.of(filterModel), Sort.by(Sort.Direction.DESC, "name"))).thenReturn(models);

        // Chamada ao método a ser testado
        List<AdditionalItem> result = adapter.searchAdditionalItems(productCategoryId);

        // Verificações
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sauce", result.get(0).getName());
    }

    @Test
    void testSearchAdditionalItems_NoFilter() {
        // Setup para nenhum filtro
        AdditionalItemModel filterModel = AdditionalItemModel.builder().build();  // Não há associação com ProductCategory
        AdditionalItemModel foundModel = new AdditionalItemModel("2", "Cheese", "Extra cheese", new BigDecimal("3.00"), null, ZonedDateTime.now(), ZonedDateTime.now());
        List<AdditionalItemModel> models = List.of(foundModel);

        // Comportamento esperado
        when(springDataRepository.findAll(Example.of(filterModel), Sort.by(Sort.Direction.DESC, "name"))).thenReturn(models);

        // Chamada ao método a ser testado
        List<AdditionalItem> result = adapter.searchAdditionalItems(null);  // `null` para simular "sem filtro"

        // Verificações
        assertNotNull(result);
        assertEquals(1, result.size());
        assertNull(result.get(0).getProductCategoryId());  // Verifica que o productCategoryId é nulo
        assertEquals("Cheese", result.get(0).getName());
    }
}
