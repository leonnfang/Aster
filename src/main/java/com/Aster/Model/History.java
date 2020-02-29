package com.Aster.Model;
import org.springframework.stereotype.Repository;

import java.util.*;

public class History {
    private List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
