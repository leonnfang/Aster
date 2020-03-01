package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FloristDB extends UserDB {
    Map<String,Florist> floristMap = new HashMap<>();

    public int addFlorist(Florist florist) throws Exception {
        System.out.println("in db layer");
        if(florist == null || floristMap.containsKey(florist.getEmail())){
            throw new Exception("Invalid florist");
        }
        floristMap.put(florist.getEmail(),florist);
        System.out.println("Florist was created successfully");
        return 0;

    }

    @Override
    public int deleteUser(String email) throws Exception {
        return super.deleteUser(email);
    }

    @Override
    public int addOrder(Order order) {
        if(order == null){
            throw new NullPointerException("order pointer is null");
        }
        int quantity = order.getQuantity();
        Product soldProduct = order.getProduct();
        Florist florist = order.getFlorist();
        Map<Product,Integer> productIntegerMap = florist.getInventory().getProductIntegerMap();
        //check if inventory
        if(productIntegerMap.get(soldProduct) - quantity < 0){
            //throw new Exception("Low Inventory");
        }else{
            productIntegerMap.put(soldProduct,productIntegerMap.get(soldProduct)-quantity);
        }

        return 0;
    }

    @Override
    public int cancelOrder(Order order) {
        return super.cancelOrder(order);
    }

    @Override
    public History getHistory(User user) {
        return super.getHistory(user);
    }

    @Override
    public Inventory getInventory(Florist florist) {
        return super.getInventory(florist);
    }

    @Override
    public List<Product> getProduct() {
        return super.getProduct();
    }
    public int updateInventory(Florist florist, Product product, int quantity) throws Exception {
        if(UserMap.get(florist.getEmail()) == null){
            throw new Exception("Florist dose not exist");
        }
        Inventory inventory = florist.getInventory();
        if(!inventory.getProductIntegerMap().containsKey(product)){
            inventory.getProductIntegerMap().put(product,quantity);
            return 0;
        }
        int newquantity = inventory.getProductIntegerMap().get(product) + quantity;
        if(newquantity < 0){
            throw new Exception("Update cannot be satisfied due to quantity");
        }else{
            inventory.getProductIntegerMap().put(product,newquantity);
        }
        return 0;
    }
}
