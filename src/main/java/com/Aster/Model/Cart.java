package com.Aster.Model;
import java.util.*;

public class Cart {
    private List<Order> cart = new ArrayList<>();

    public List<Order> getOrderList() {
        return cart;
    }

    public void setOrderList(List<Order> orderList) {
        this.cart = orderList;
    }
}
