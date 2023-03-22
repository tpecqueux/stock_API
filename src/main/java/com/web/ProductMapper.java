package com.web;

import com.web.model.DTOProduct;
import com.web.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static DTOProduct map(Product product) {
        DTOProduct dtoProduct = new DTOProduct();
        dtoProduct.setId(product.getId());
        dtoProduct.setName(product.getName());
        dtoProduct.setPrice(product.getPrice());
        dtoProduct.setQuantity(product.getQuantity());
        dtoProduct.setSupplier(product.getSupplier());
        return dtoProduct;
    }

    public static Product map(DTOProduct dtoProduct) {
        Product product = new Product();
        product.setId(dtoProduct.getId());
        product.setName(dtoProduct.getName());
        product.setPrice(dtoProduct.getPrice());
        product.setQuantity(dtoProduct.getQuantity());
        product.setSupplier(dtoProduct.getSupplier());
        return product;
    }

    public static List<Product> maps(List<DTOProduct> dtoProduct) {
        List<Product> res = new ArrayList<>();
        dtoProduct.forEach(prd -> {
            Product product = new Product();
            product.setId(prd.getId() == null ? 0 : prd.getId());
            product.setName(prd.getName());
            product.setPrice(prd.getPrice());
            product.setQuantity(prd.getQuantity());
            product.setSupplier(prd.getSupplier());
            res.add(product);
        });

        return res;
    }


}
