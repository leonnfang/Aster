package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cart {
    //private List<Order> cart = new ArrayList<>();
    private List<Order> cart;

    public Cart(@JsonProperty("cart") List<Order> cart){
        this.cart = cart;
    }

    public Cart(){

    }

    public List<Order> getCart() {
        return cart;
    }

    public void setCart(List<Order> cart) {
        this.cart = cart;
    }
}
