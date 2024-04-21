package com.fiap.production.infrastructure.web;

import com.fiap.production.application.usecases.CreateProductCategoryUseCase;
import com.fiap.production.application.usecases.SearchProductCategoryUseCase;
import com.fiap.production.domain.entity.ProductCategory;
import com.fiap.production.interfaces.dto.ProductCategoryCreationRequest;
import com.fiap.production.interfaces.mapper.ProductCategoryRestMapper;
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
@WebMvcTest(controllers = ProductCategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@Import(ProductCategoryRestMapper.class)
class ProductCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductCategoryUseCase createUseCase;

    @MockBean
    private SearchProductCategoryUseCase searchUseCase;

    @Autowired
    private ProductCategoryRestMapper restMapper;

    @Test
    void createProductCategory() throws Exception {
        ProductCategoryCreationRequest request = new ProductCategoryCreationRequest("Beverages", "Drinks of all types");
        ProductCategory productCategory = ProductCategory.builder()
                .id("1")
                .name("Beverages")
                .description("Drinks of all types")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        
        given(createUseCase.createProductCategory(any(ProductCategory.class))).willReturn(productCategory);

        mockMvc.perform(post("/product_categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Beverages\", \"description\": \"Drinks of all types\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Beverages"));
    }

    @Test
    void searchProductCategories() throws Exception {
        ProductCategory productCategory = ProductCategory.builder()
                .id("1")
                .name("Beverages")
                .description("Drinks of all types")
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .build();
        List<ProductCategory> categories = Arrays.asList(productCategory);

        given(searchUseCase.searchProductCategories()).willReturn(categories);

        mockMvc.perform(get("/product_categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Beverages"));
    }

    @Test
    void createProductCategory_InvalidRequest() throws Exception {
        mockMvc.perform(post("/product_categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"\", \"description\": \"\" }"))
                .andExpect(status().isBadRequest());
    }
}
