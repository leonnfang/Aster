package com.Aster.Repository;

import com.Aster.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDB {
    Map<String, List<Product>> productMap = new HashMap<>();

    //adding the florist name to the list and making a new key if it is a new type
    public int addProduct(String flowerName, Product product) throws Exception{
        if(productMap.containsKey(flowerName)){
            if(productMap.get(flowerName).contains(product)){
                throw new Exception("This Florist already owns this flower type!");
            }
            else productMap.get(flowerName).add(product);
        }
        else{
            List<Product> newproductList = new ArrayList<>();
            newproductList.add(product);
            productMap.put(flowerName, newproductList);
        }
        return 0;
    }

    



}
