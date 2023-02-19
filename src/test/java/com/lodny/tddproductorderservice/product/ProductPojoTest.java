package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductPojoTest {

    private ProductController productController;
    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);
        productController = new ProductController(productService);
    }

    @Test
    void registerProductTest() {
        final String name = "상품명";
        final int price = 1000;
        final AddProductRequest addProductRequest = new AddProductRequest(name, price, DiscountPolicy.NONE);
        productController.addProduct(addProductRequest);
    }

}
