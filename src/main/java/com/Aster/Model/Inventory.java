package com.Aster.Model;
import com.Aster.Model.*;
import java.util.*;
public class Inventory {
    Map<Product,Integer> productIntegerMap;
    // products + information
    // bouquet + information
    //List<Pair<Product,Integer>> list = new ArrayList<>();

    public Inventory() {
        productIntegerMap = new HashMap<>();
    }

    public void setProductIntegerMap(Map<Product, Integer> productIntegerMap) {
        this.productIntegerMap = productIntegerMap;
    }

    public Map<Product, Integer> getProductIntegerMap() {
        return productIntegerMap;
    }
}
