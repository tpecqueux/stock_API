package com.web.business;

import com.web.SupplierMapper;
import com.web.model.DTOSupplier;
import com.web.model.Supplier;
import com.web.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SupplierBusiness {
    @Autowired
    private SupplierService supplierService;

    public Flux<DTOSupplier> getAll(){
        return Flux.fromStream(supplierService.getAll().stream().map(SupplierMapper::map));
    }

    public Mono<DTOSupplier> setOne(DTOSupplier dtoSupplier){
        return Mono.just(SupplierMapper.map(supplierService.setOne(SupplierMapper.map(dtoSupplier))));
    }

    public Flux<DTOSupplier> getLike(String name) {
        return Flux.fromStream(supplierService.getLike(name).stream().map(SupplierMapper::map));
    }
}
