package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDITest {

    @Autowired
    private ProductController productController;

    @Test
    void registerProductTest() {
        final String name = "상품명";
        final int price = 1000;
        final AddProductRequest addProductRequest = new AddProductRequest(name, price, DiscountPolicy.NONE);
        productController.addProduct(addProductRequest);
    }

}
