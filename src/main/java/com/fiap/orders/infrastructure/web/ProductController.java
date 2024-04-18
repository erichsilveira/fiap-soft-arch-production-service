package com.fiap.orders.infrastructure.web;

import com.fiap.techchallenge.application.usecases.CreateProductUseCase;
import com.fiap.techchallenge.application.usecases.DeleteProductUseCase;
import com.fiap.techchallenge.application.usecases.SearchProductUseCase;
import com.fiap.techchallenge.application.usecases.UpdateProductUseCase;
import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.exception.ResourceNotFoundException;
import com.fiap.techchallenge.interfaces.dto.DeleteProductCommand;
import com.fiap.techchallenge.interfaces.dto.ProductCreationRequest;
import com.fiap.techchallenge.interfaces.dto.ProductUpdateRequest;
import com.fiap.techchallenge.interfaces.dto.SearchProductCommand;
import com.fiap.techchallenge.interfaces.dto.UpdateProductCommand;
import com.fiap.techchallenge.interfaces.mapper.ProductRestMapper;
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
