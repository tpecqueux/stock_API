package com.web.service;

import com.web.model.Supplier;
import com.web.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAll() {
        return supplierRepository.findAll();
    }

    public Supplier getOne(Integer id) {
        return supplierRepository.findById(id).get();
    }

    public Supplier setOne(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getLike(String name) {
        return supplierRepository.findByNameContains(name);
    }
}
