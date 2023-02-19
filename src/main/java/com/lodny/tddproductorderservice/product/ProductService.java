package com.lodny.tddproductorderservice.product;

import org.springframework.stereotype.Service;

@Service
class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }
}
