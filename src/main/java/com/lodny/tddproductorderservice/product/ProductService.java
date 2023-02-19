package com.lodny.tddproductorderservice.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }

    public Product getProduct(final Long productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
    }

    @Transactional
    public Product updateProduct(final Long productId, final Product updateProduct) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        product.update(updateProduct.getName(), updateProduct.getPrice(), updateProduct.getDiscountPolicy());

        return product;
    }
}
