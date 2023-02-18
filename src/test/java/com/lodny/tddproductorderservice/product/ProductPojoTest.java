package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

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
        public void addProduct(final AddProductRequest addProductRequest) {
            throw new UnsupportedOperationException("Unsupported addProduct");
        }
    }
}
