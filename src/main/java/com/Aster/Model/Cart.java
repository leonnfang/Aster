package com.Aster.Model;
import java.util.*;

public class Cart {
    private List<Order> cart = new ArrayList<>();

    public List<Order> getCartList() {
        return cart;
    }

    public void setCartList(List<Order> orderList) {
        this.cart = orderList;
    }
}
