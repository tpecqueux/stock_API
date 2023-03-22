package com.web.business;

import com.web.Exception.IdNotFound;
import com.web.model.DTOProduct;
import com.web.model.Product;
import com.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.lang.reflect.Array;
import java.util.*;

@Component
public class ProductBusiness {
    @Autowired
    ProductService productService;

    public Mono<Product> getOne(Integer id){
        Optional<Product> product = productService.getOne(id);
        return Mono
                .just(product)
                .handle((pdt, sink) -> {
                    if(pdt.isEmpty())
                        sink.error(new NoSuchElementException("ID not found"));
                    else
                        sink.next(pdt.get());
                });
    }

    public Flux<Product> getAll(){
        System.out.println(productService.getAll());
        return Flux.fromStream(productService.getAll().stream()
                .filter(product -> product.getPrice() != 0));
    }

    public Flux<Product> setProducts(Flux<DTOProduct> products) {
        List<Product> listProducts = productService.setProducts(products.collectList().block());
        return Flux
                .fromStream(listProducts.stream());
    }

    public Mono<Product> changeSupplier(Integer productId, Integer supplierId) {
        try {
            return Mono
                    .justOrEmpty(productService.changeSupplier(productId, supplierId));
        } catch (IdNotFound e) {
            return Mono.error(e);
        }
    }
}
