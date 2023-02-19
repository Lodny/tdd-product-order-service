package com.lodny.tddproductorderservice.product;

class ProductController {
    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    public void addProduct(final AddProductRequest addProductRequest) {
        productService.addProduct(addProductRequest);
    }
}
