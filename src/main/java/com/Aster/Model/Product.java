package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private String name;
    private double price;
    private String description;
    private String storeName;

    public Product(@JsonProperty String name,
                   @JsonProperty double price,
                   @JsonProperty String discription,
                   @JsonProperty String storeName) {
        this.name = name;
        this.price = price;
        this.description = discription;
        this.storeName = storeName;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
