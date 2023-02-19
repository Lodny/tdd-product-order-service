package com.lodny.tddproductorderservice.product;

import org.springframework.util.Assert;

record GetProductResponse(Long id, String name, int price, DiscountPolicy discountPolicy) {
    GetProductResponse {
        Assert.notNull(id, "상품 ID는 필수 입니다.");
        Assert.hasText(name, "상품 명은 필수 입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수 입니다");
    }
}
