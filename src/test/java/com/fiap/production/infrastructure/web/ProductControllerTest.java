package com.fiap.production.infrastructure.web;

import com.fiap.production.application.usecases.CreateProductUseCase;
import com.fiap.production.application.usecases.DeleteProductUseCase;
import com.fiap.production.application.usecases.SearchProductUseCase;
import com.fiap.production.application.usecases.UpdateProductUseCase;
import com.fiap.production.domain.entity.Product;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.interfaces.dto.ProductCreationRequest;
import com.fiap.production.interfaces.dto.ProductUpdateRequest;
import com.fiap.production.interfaces.mapper.ProductRestMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@Import(ProductRestMapper.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateProductUseCase createUseCase;

    @MockBean
    private UpdateProductUseCase updateUseCase;

    @MockBean
    private DeleteProductUseCase deleteUseCase;

    @MockBean
    private SearchProductUseCase searchUseCase;

    @Autowired
    private ProductRestMapper restMapper;

    @Test
    void createProduct() throws Exception {
        Product product = new Product("1", "Coffee", "Rich Aroma", new BigDecimal("10.00"), "123", ZonedDateTime.now(), ZonedDateTime.now());
        ProductCreationRequest creationRequest = new ProductCreationRequest("Coffee", "Rich Aroma", "123", new BigDecimal("10.00"));
        
        given(createUseCase.createProduct(any(Product.class))).willReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Coffee\", \"description\": \"Rich Aroma\", \"productCategoryId\": \"123\", \"price\": 10.00 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coffee"));
    }

    @Test
    void searchProductById_NotFound() throws Exception {
        given(searchUseCase.searchProductById(any())).willThrow(new ResourceNotFoundException());

        mockMvc.perform(get("/products/{id}", "999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_NotFound() throws Exception {
        ProductUpdateRequest updateRequest = new ProductUpdateRequest("Coffee", "Updated Aroma", "cat1", new BigDecimal("12.00"));

        given(updateUseCase.updateProduct(any())).willThrow(new ResourceNotFoundException());

        mockMvc.perform(put("/products/{id}", "999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Coffee\", \"description\": \"Updated Aroma\", \"price\": 12.00, \"productCategoryId\": \"123\" }"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProduct() throws Exception {
        willDoNothing().given(deleteUseCase).deleteProduct(any());

        mockMvc.perform(delete("/products/{id}", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void searchProductsWithFilter() throws Exception {
        Product product = new Product("1", "Tea", "Green Tea", new BigDecimal("5.00"), "124", ZonedDateTime.now(), ZonedDateTime.now());
        List<Product> products = Arrays.asList(product);

        given(searchUseCase.searchProducts(any())).willReturn(products);

        mockMvc.perform(get("/products").param("productCategoryId", "124"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tea"));
    }
}
