package com.fiap.orders.infrastructure.adapter;

import com.fiap.techchallenge.domain.entity.Product;
import com.fiap.techchallenge.domain.repository.ProductRepository;
import com.fiap.techchallenge.exception.ResourceNotFoundException;
import com.fiap.techchallenge.infrastructure.model.ProductCategoryModel;
import com.fiap.techchallenge.infrastructure.model.ProductModel;
import com.fiap.techchallenge.infrastructure.persistence.ProductJpaRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository springDataRepository;

    @Override
    public Product createProduct(Product product) {
        var model = ProductModel.toProductModel(product);

        springDataRepository.save(model);

        return ProductModel.toProduct(model);
    }

    @Override
    public List<Product> searchProducts(String productCategoryId) {
        var filter = ProductModel.builder();

        if (StringUtils.isNotBlank(productCategoryId)) {
            filter.productCategory(ProductCategoryModel.builder().id(productCategoryId).build());
        }

        var entities = springDataRepository.findAll(Example.of(filter.build()),
            Sort.by(Sort.Direction.DESC, "name"));

        return entities.stream()
            .map(ProductModel::toProduct).toList();
    }

    @Override
    public void deleteProduct(String entityId) {
        springDataRepository.deleteById(entityId);
    }

    @Override
    public Product updateProduct(String entityId, Product product)
        throws ResourceNotFoundException {
        var model = springDataRepository.findById(entityId)
            .orElseThrow(ResourceNotFoundException::new);

        model.setDescription(product.getDescription());
        model.setName(product.getName());
        model.setPrice(product.getPrice());

        springDataRepository.save(model);

        return ProductModel.toProduct(model);
    }
}
