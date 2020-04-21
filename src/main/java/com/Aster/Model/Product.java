package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", nullable = false, unique = true)
    private Long Id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column
    private String description;
    @Column(nullable = false)
    private String florsitEmail;
    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTORY_ID")
    @JsonIgnore
    private Inventory inventory;

    public Product(@JsonProperty("productName") String productName,
                   @JsonProperty("price") double price,
                   @JsonProperty("description") String description,
                   @JsonProperty("floristEmail") String floristEmail,
                   @JsonProperty("quantity") int quantity) {
        this.name = productName;
        this.price = price;
        this.description = description;
        this.florsitEmail = floristEmail;
        this.quantity = quantity;
    }

    public Product(){}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFlorsitEmail() {
        return florsitEmail;
    }

    public void setFlorsitEmail(String florsitEmail) {
        this.florsitEmail = florsitEmail;
    }
}
