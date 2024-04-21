package com.fiap.production.infrastructure.web;

import com.fiap.production.application.usecases.CreateProductUseCase;
import com.fiap.production.application.usecases.DeleteProductUseCase;
import com.fiap.production.application.usecases.SearchProductUseCase;
import com.fiap.production.application.usecases.UpdateProductUseCase;
import com.fiap.production.domain.entity.Product;
import com.fiap.production.exception.ResourceNotFoundException;
import com.fiap.production.interfaces.dto.*;
import com.fiap.production.interfaces.mapper.ProductRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createUseCase;

    private final UpdateProductUseCase updateUseCase;

    private final DeleteProductUseCase deleteUseCase;

    private final SearchProductUseCase searchUseCase;

    private final ProductRestMapper restMapper;

    @PostMapping
    ResponseEntity<Product> create(
        @RequestBody @Valid ProductCreationRequest registrationRequest) {
        var domainEntity = createUseCase.createProduct(
            restMapper.toDomainEntity(registrationRequest));

        return new ResponseEntity<>(domainEntity, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Product>> search(
        @RequestParam(required = false) String productCategoryId) {
        var domainEntities = searchUseCase.searchProducts(
            new SearchProductCommand(productCategoryId));

        return ResponseEntity.ok(domainEntities);
    }

    @GetMapping("/{id}")
    ResponseEntity<Product> searchById(
            @PathVariable String id) throws ResourceNotFoundException {

        try {
            var domainEntity = searchUseCase.searchProductById(
                new SearchProductByIdCommand(id));
            return ResponseEntity.ok(domainEntity);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> update(@PathVariable String id,
        @RequestBody @Valid ProductUpdateRequest updateRequest) {
        try {
            var domainEntity = updateUseCase.updateProduct(new UpdateProductCommand(id,
                Product.builder().name(updateRequest.name())
                    .description(updateRequest.description())
                    .price(updateRequest.price())
                    .productCategoryId(updateRequest.productCategoryId())
                    .build()));

            return new ResponseEntity<>(domainEntity, HttpStatus.OK);

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String id) {
        deleteUseCase.deleteProduct(new DeleteProductCommand(id));
    }
}
