package com.web;

import com.web.model.DTOProduct;
import com.web.model.DTOSupplier;
import com.web.model.Supplier;

public class SupplierMapper {
    public static DTOSupplier map(Supplier supplier){
        DTOSupplier dtoSupplier = new DTOSupplier();
        dtoSupplier.setId(supplier.getId());
        dtoSupplier.setName(supplier.getName());
        dtoSupplier.setProducts(supplier.getProducts());
        return dtoSupplier;
    }

    public static Supplier map(DTOSupplier dtoSupplier){
        Supplier supplier = new Supplier();
        supplier.setId(dtoSupplier.getId());
        supplier.setName(dtoSupplier.getName());
        supplier.setProducts(dtoSupplier.getProducts());
        return supplier;
    }
}
