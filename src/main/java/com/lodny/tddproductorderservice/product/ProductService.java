package com.lodny.tddproductorderservice.product;

class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(final AddProductRequest addProductRequest) {
        Product product = new Product(addProductRequest.name(), addProductRequest.price(), addProductRequest.discountPolicy());
        productRepository.save(product);
    }
}
