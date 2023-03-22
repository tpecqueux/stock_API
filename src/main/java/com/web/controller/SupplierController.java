package com.web.controller;

import com.web.SupplierMapper;
import com.web.business.SupplierBusiness;
import com.web.model.DTOSupplier;
import com.web.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierBusiness supplierBusiness;
    @GetMapping
    public Flux<DTOSupplier> getAll(){
        return supplierBusiness.getAll();
    }

    @PostMapping("/set")
    public Mono<DTOSupplier> setOne(@RequestBody DTOSupplier dtoSupplier){
        return supplierBusiness.setOne(dtoSupplier);
    }

    @GetMapping("/name/{name}")
    public Flux<DTOSupplier> getOne(@PathVariable String name){
        return supplierBusiness.getLike(name);
    }
}
