package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

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

    private record AddProductRequest(String name, int price, DiscountPolicy discountPolicy) {
        private AddProductRequest {
            Assert.hasText(name, "상품 명은 필수 입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수 입니다");
        }
    }

    private enum DiscountPolicy {
        NONE
    }

    private static class ProductController {
        private final ProductService productService;

        public ProductController(final ProductService productService) {
            this.productService = productService;
        }

        public void addProduct(final AddProductRequest addProductRequest) {
            productService.addProduct(addProductRequest);
        }
    }

    private static class ProductService {
        private final ProductRepository productRepository;

        public ProductService(final ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public void addProduct(final AddProductRequest addProductRequest) {
            Product product = new Product(addProductRequest.name, addProductRequest.price, addProductRequest.discountPolicy);
            productRepository.save(product);
        }
    }

    private static class Product {
        private Long id;
        private String name;
        private int price;
        private DiscountPolicy discountPolicy;

        public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
            Assert.hasText(name, "상품 명은 필수 입니다.");
            Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
            Assert.notNull(discountPolicy, "할인 정책은 필수 입니다");
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private static class ProductRepository {

        private final Map<Long, Product> persistence = new HashMap<>();
        private Long sequence = 0L;

        public void save(final Product product) {
            product.setId(++sequence);
            persistence.put(sequence, product);
        }
    }
}
