package com.lodny.tddproductorderservice.product;

import com.lodny.tddproductorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {

    @Test
    void registerProductTest() {
        final AddProductRequest addProductRequest = 상품생성();
        final ExtractableResponse<Response> response = 상품등록(addProductRequest);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static ExtractableResponse<Response> 상품등록(final AddProductRequest addProductRequest) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(addProductRequest)
                .when()
                .post("/products")
                .then()
                .log().all().extract();
    }

    private static AddProductRequest 상품생성() {
        final String name = "상품명";
        final int price = 1000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new AddProductRequest(name, price, discountPolicy);
    }

}
