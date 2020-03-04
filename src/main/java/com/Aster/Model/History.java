package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public class History {
    //private List<Order> orderList = new ArrayList<>();
    private List<Order> history;

    public History(@JsonProperty("history") List<Order> history) {
        this.history = history;
    }

    public History(){

    }

    public List<Order> getHistory() {
        return history;
    }

    public void setHistory(List<Order> history) {
        this.history = history;
    }
}
