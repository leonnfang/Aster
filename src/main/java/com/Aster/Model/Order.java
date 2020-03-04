package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    private String floristEmail;
    private String customerEmail;
    private String date;
    private Product product;
    private int quantity;

    public Order(@JsonProperty("florist") String floristEmail,
                 @JsonProperty("customer") String customerEmail,
                 @JsonProperty("date") String date,
                 @JsonProperty("product") Product product,
                 @JsonProperty("quantity") int quantity){
        this.floristEmail = floristEmail;
        this.customerEmail = customerEmail;
        this.date = date;
        this.product = product;
        this.quantity = quantity;
    }

    public String getFloristEmail() {
        return floristEmail;
    }

    public void setFloristEmail(String floristEmail) {
        this.floristEmail = floristEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
