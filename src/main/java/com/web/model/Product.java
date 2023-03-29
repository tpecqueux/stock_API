package com.web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Repository;

@Repository
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column
    private Float price;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

    public Product(){}

    public Product(Integer id, String name, Float price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price < 0 ? 0 : price;
        this.quantity = stock;
    }

    public String toString(){
        return "{id=" + this.id + ", name=" + this.name + ", price=" + this.price + ", quantity= " + this.quantity + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
