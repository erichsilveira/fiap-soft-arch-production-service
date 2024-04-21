package com.fiap.production.infrastructure.web;

import com.fiap.production.application.usecases.CreateAdditionalItemUseCase;
import com.fiap.production.application.usecases.SearchAdditionalItemUseCase;
import com.fiap.production.domain.entity.AdditionalItem;
import com.fiap.production.interfaces.dto.AdditionalItemCreationRequest;
import com.fiap.production.interfaces.mapper.AdditionalItemRestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdditionalItemController.class)
@AutoConfigureMockMvc(addFilters = false) // Disable Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@Import(AdditionalItemRestMapper.class)
class AdditionalItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateAdditionalItemUseCase createUseCase;

    @MockBean
    private SearchAdditionalItemUseCase searchUseCase;

    @Autowired
    private AdditionalItemRestMapper restMapper;

    @Test
    void createAdditionalItem() throws Exception {
        AdditionalItemCreationRequest request = new AdditionalItemCreationRequest("Coffee", "Aromatic blend", "123", new BigDecimal("5.00"));
        AdditionalItem additionalItem = AdditionalItem.builder()
                .id("1")
                .name("Coffee")
                .description("Aromatic blend")
                .price(new BigDecimal("5.00"))
                .productCategoryId("123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        
        given(createUseCase.createAdditionalItem(any(AdditionalItem.class))).willReturn(additionalItem);

        mockMvc.perform(post("/additional_items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Coffee\", \"description\": \"Aromatic blend\", \"productCategoryId\": \"123\", \"price\": 5.00 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coffee"));
    }

    @Test
    void searchAdditionalItems_WithFilter() throws Exception {
        AdditionalItem additionalItem = AdditionalItem.builder()
                .id("1")
                .name("Coffee")
                .description("Aromatic blend")
                .price(new BigDecimal("5.00"))
                .productCategoryId("123")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        List<AdditionalItem> items = Arrays.asList(additionalItem);

        given(searchUseCase.searchAdditionalItems(any())).willReturn(items);

        mockMvc.perform(get("/additional_items")
                        .param("productCategoryId", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Coffee"));
    }

    @Test
    void createAdditionalItem_InvalidRequest() throws Exception {
        String invalidRequestContent = "{ \"name\": \"\", \"description\": \"Aromatic blend\", \"productCategoryId\": \"123\", \"price\": 5.00 }";

        mockMvc.perform(post("/additional_items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequestContent))
                .andExpect(status().isBadRequest()); // Assuming you have validation in place
    }

    @Test
    void searchAdditionalItems_NoResults() throws Exception {
        given(searchUseCase.searchAdditionalItems(any())).willReturn(Arrays.asList());

        mockMvc.perform(get("/additional_items")
                        .param("productCategoryId", "nonexistent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
