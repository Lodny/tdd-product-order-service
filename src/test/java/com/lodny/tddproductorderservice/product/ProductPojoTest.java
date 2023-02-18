package com.lodny.tddproductorderservice.product;

import org.junit.jupiter.api.Test;

public class ProductPojoTest {

    @Test
    void registerProductTest() {
        final AddProductRequest addProductRequest = new AddProductRequest("상품명", 1000, DiscountPolicy.NONE);
        productController.addProduct(addProductRequest);
    }
}
