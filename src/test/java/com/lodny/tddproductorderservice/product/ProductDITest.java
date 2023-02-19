package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class ProductDITest {

    @Autowired
    private ProductController productController;

    @Test
    void registerProductTest() {
        final AddProductRequest addProductRequest = 상품생성();
        productController.addProduct(addProductRequest);
    }

    private static AddProductRequest 상품생성() {
        final String name = "상품명";
        final int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

}
