package com.lodny.tddproductorderservice.product;

import com.lodny.tddproductorderservice.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductApiTest extends ApiTest {

    @Test
    void registerProductTest() {
        final ExtractableResponse<Response> response = 상품등록(상품생성());
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void queryProductTest() {
        AddProductRequest request = 상품생성();
        상품등록(request);

        final Long productId = 1L;
        ExtractableResponse<Response> response = 상품조회(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getString("name")).isEqualTo(request.name());
        assertThat(response.body().jsonPath().getInt("price")).isEqualTo(request.price());
        assertThat(response.body().jsonPath().getString("discountPolicy")).isEqualTo(request.discountPolicy().name());
    }

    @Test
    void updateProductTest() {
        상품등록(상품생성());

        final Long productId = 1L;
        UpdateProductRequest updateProductRequest = 상품수정생성();
        상품수정(productId, updateProductRequest);
        ExtractableResponse<Response> response = 상품조회(productId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getString("name")).isEqualTo(updateProductRequest.name());
        assertThat(response.body().jsonPath().getInt("price")).isEqualTo(updateProductRequest.price());
        assertThat(response.body().jsonPath().getString("discountPolicy")).isEqualTo(updateProductRequest.discountPolicy().name());
    }

    private static ExtractableResponse<Response> 상품수정(final Long productId, final UpdateProductRequest updateProductRequest) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updateProductRequest)
                .when()
                .patch("/products/{productId}", productId)
                .then().log().all()
                .extract();
        return response;
    }

    private static UpdateProductRequest 상품수정생성() {
        String name = "상품 수정";
        int price = 2000;
        DiscountPolicy discountPolicy = DiscountPolicy.NONE;
        return new UpdateProductRequest(name, price, discountPolicy);
    }

    private static ExtractableResponse<Response> 상품조회(final Long productId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract();
        return response;
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
