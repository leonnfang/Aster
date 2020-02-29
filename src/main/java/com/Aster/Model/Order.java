package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

public class Order {
    private Florist florist;
    private Customer customer;
    private String date;
    private Product product;

    public Order() {
    }

    public Order(@JsonProperty Florist florist,
                 @JsonProperty Customer customer,
                 @JsonProperty String date,
                 @JsonProperty Product product) {
        this.florist = florist;
        this.customer = customer;
        this.date = date;
        this.product = product;
    }

    public Florist getFlorist() {
        return florist;
    }

    public void setFlorist(Florist florist) {
        this.florist = florist;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
