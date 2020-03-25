package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cart {
    //private List<Order> cart = new ArrayList<>();
    private List<Order> cart;
    private double totalprice;

    public Cart(@JsonProperty("cart") List<Order> cart,
                @JsonProperty("totalprice") double totalprice){
        this.cart = cart;
        this.totalprice = totalprice;
    }

    public Cart(){

    }

    public List<Order> getCartList() {
        return cart;
    }

    public void setCart(List<Order> cart) {
        this.cart = cart;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
