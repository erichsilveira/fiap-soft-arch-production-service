package com.fiap.orders.infrastructure.web;

import com.fiap.techchallenge.application.usecases.CreateProductCategoryUseCase;
import com.fiap.techchallenge.application.usecases.SearchProductCategoryUseCase;
import com.fiap.techchallenge.domain.entity.ProductCategory;
import com.fiap.techchallenge.interfaces.dto.ProductCategoryCreationRequest;
import com.fiap.techchallenge.interfaces.mapper.ProductCategoryRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product_categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final CreateProductCategoryUseCase createUseCase;

    private final SearchProductCategoryUseCase searchUseCase;

    private final ProductCategoryRestMapper restMapper;

    @PostMapping
    ResponseEntity<ProductCategory> create(
        @RequestBody @Valid ProductCategoryCreationRequest registrationRequest) {
        var domainEntity = createUseCase.createProductCategory(
            restMapper.toDomainEntity(registrationRequest));

        return new ResponseEntity<>(domainEntity, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<ProductCategory>> search() {
        var domainEntities = searchUseCase.searchProductCategories();

        return ResponseEntity.ok(domainEntities);
    }
}
