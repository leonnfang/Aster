package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public class History {
    //private List<Order> orderList = new ArrayList<>();
    private List<Order> orderList;

    public History(@JsonProperty("orderList") List<Order> orderList) {
        this.orderList = orderList;
    }

    public History(){

    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
