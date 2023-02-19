package com.lodny.tddproductorderservice.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "t_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

    public Product(final String name, final int price, final DiscountPolicy discountPolicy) {
        update(name, price, discountPolicy);
    }

    public void update(final String name, final int price, final DiscountPolicy discountPolicy) {
        Assert.hasText(name, "상품 명은 필수 입니다.");
        Assert.isTrue(price > 0, "상품 가격은 0보다 커야 합니다.");
        Assert.notNull(discountPolicy, "할인 정책은 필수 입니다");
        this.name = name;
        this.price = price;
        this.discountPolicy = discountPolicy;
    }
}
