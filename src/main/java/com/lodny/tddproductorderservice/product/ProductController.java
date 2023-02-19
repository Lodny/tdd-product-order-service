package com.lodny.tddproductorderservice.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long productId) {
        Product product = productService.getProduct(productId);
        GetProductResponse getProductResponse = new GetProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy());

        return ResponseEntity.ok(getProductResponse);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<GetProductResponse> updateProduct(@RequestBody final UpdateProductRequest updateProductRequest,
                                           @PathVariable Long productId) {
        final Product updateProduct = new Product(updateProductRequest.name(), updateProductRequest.price(), updateProductRequest.discountPolicy());
        Product product = productService.updateProduct(productId, updateProduct);

        GetProductResponse getProductResponse = new GetProductResponse(product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscountPolicy());

        return ResponseEntity.ok(getProductResponse);
    }
}
