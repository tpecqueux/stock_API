package com.web.service;

import com.web.Exception.IdNotFound;
import com.web.ProductMapper;
import com.web.SupplierMapper;
import com.web.model.DTOProduct;
import com.web.model.Supplier;
import com.web.repository.ProductRepository;
import com.web.model.Product;
import com.web.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Product> getAll() {
        return productRepository.findAll(Sort.by("price"));
    }

    public Optional<Product> getOne(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> setProducts(List<DTOProduct> products) {
        return productRepository.saveAll(ProductMapper.maps(products));
    }

    public Optional<Product> changeSupplier(Integer productId, Integer supplierId) throws IdNotFound {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new IdNotFound("product id not found");
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        if(supplier.isEmpty())
            throw new IdNotFound("supplier id not found");
        product.get().setSupplier(supplier.get());
        return Optional.of(productRepository.save(product.get()));
    }
}
