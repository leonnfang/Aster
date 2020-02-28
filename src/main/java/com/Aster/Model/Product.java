package com.Aster.Model;

public class Product {
    private String name;
    private double price;
    private String descirption;
    private String storeName;

    public Product(String name, double price, String descirption, String storeName) {
        this.name = name;
        this.price = price;
        this.descirption = descirption;
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

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
