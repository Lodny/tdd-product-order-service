package com.lodny.tddproductorderservice.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody final AddProductRequest addProductRequest) {
        final Product product = new Product(addProductRequest.name(), addProductRequest.price(), addProductRequest.discountPolicy());
        productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
