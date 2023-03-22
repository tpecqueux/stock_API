package com.web.controller;

import com.web.business.ProductBusiness;
import com.web.model.DTOProduct;
import com.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductBusiness productBusiness = new ProductBusiness();

    @GetMapping
    public Flux<Product> getAll(){
        return productBusiness.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getOne(@PathVariable Integer id) {
        return productBusiness.getOne(id);
    }

    @PostMapping
    public Flux<Product> setAll(@RequestBody List<DTOProduct> products) {
        System.out.println(products);
        return productBusiness.setProducts(Flux.fromStream(products.stream()));
    }

    @PutMapping("/changeSupplier/{productId}/{supplierId}")
    public Mono<Product> changeSupplier(@PathVariable Integer productId, @PathVariable Integer supplierId) {
        return productBusiness.changeSupplier(productId, supplierId);
    }
}