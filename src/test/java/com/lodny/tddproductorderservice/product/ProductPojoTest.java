package com.lodny.tddproductorderservice.product;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class ProductPojoTest {

    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController();
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
        private ProductService productService;

        public void addProduct(final AddProductRequest addProductRequest) {
            productService.addProduct(addProductRequest);
        }
    }

    private static class ProductService {
        private ProductRepository productRepository;

        public void addProduct(final AddProductRequest addProductRequest) {
            Product product = new Product(addProductRequest.name, addProductRequest.price, addProductRequest.discountPolicy);
            productRepository.save(product);
        }
    }

    private static class Product {
        private final String name;
        private final int price;
        private final DiscountPolicy discountPolicy;

        public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
            this.name = name;
            this.price = price;
            this.discountPolicy = discountPolicy;
        }
    }

    private static class ProductRepository {

        private final Map<Long, Product> productMap = new HashMap<>();
        private Long sequence = 0L;

        public void save(final Product product) {
            productMap.put(sequence++, product);
        }
    }
}
