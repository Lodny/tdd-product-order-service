package com.lodny.tddproductorderservice.product;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
class ProductRepository {

    private final Map<Long, Product> persistence = new HashMap<>();
    private Long sequence = 0L;

    public Product save(final Product product) {
        product.setId(++sequence);
        persistence.put(sequence, product);
        return product;
    }
}
