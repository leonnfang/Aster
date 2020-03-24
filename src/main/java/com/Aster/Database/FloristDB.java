package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FloristDB{
    Map<String,Florist> floristMap = new HashMap<>();

    public int addFlorist(Florist florist) throws Exception {
        if(floristMap.containsKey(florist.getEmail())){
            throw new Exception("This Florist Has Already Existed");
        }
        List<Order> newHistoryList = new ArrayList<>();

        Inventory newInventory = new Inventory(new HashMap<>(),true,0);
        History newHistory = new History(newHistoryList);
        Florist newflorist = new Florist(florist.getUser_name(),
                florist.getPassword(),
                florist.getEmail(),
                florist.getLastName(),
                florist.getFirstName(),
                florist.getAddress(),
                newInventory,
                newHistory);

        floristMap.put(florist.getEmail(),newflorist);
        return 0;
    }
    public int deleteFlorist(String email) throws Exception {
        if (!floristMap.containsKey(email)){
            throw new Exception("Florist Dose not Exist");
        }
        floristMap.remove(email);
        if(!floristMap.containsKey(email)){
            return 0;
        }
        return 1;
    }
    public Florist getFlorist(String email) {
        return floristMap.get(email);
    }
    public String getUser_name(String email) throws Exception {
        if(!floristMap.containsKey(email)){
            throw new Exception("Invalid Email Address(Dose Not Exist)");
        }
        return floristMap.get(email).getUser_name();
    }
    public boolean isvalid(String email){
        if(floristMap.containsKey(email)) return true;
        else return false;
    }


    public int addProduct(String email, Product product, int quantity) throws Exception{
        if(quantity < 0){
            throw new Exception("Number of Product Cannot be Lower than Zero");
        }
        Product newproduct = new Product(product.getName(), product.getPrice(),
                                         product.getDescription(), product.getStoreName());
        floristMap.get(email).getInventory().getInventoryMap().put(product.getName(), quantity);
        return 0;
    }
    public int updateInventory(String floristEmail, Product product, int quantity) throws Exception {
        Florist florist = floristMap.get(floristEmail);
        Inventory floristInventory = florist.getInventory();
        Map<String,Integer> inventoryMap = floristInventory.getInventoryMap();
        int numberLeft = inventoryMap.get(product.getName());
        if(!inventoryMap.containsKey(product.getName())){
            throw new Exception("Such Product Does Not Exist");
        }
        if(numberLeft + quantity < 0){
            throw new Exception("Not Enough Products Left");
        }
        inventoryMap.put(product.getName(), numberLeft+quantity);
        return 0;
    }
    public Inventory viewInventory(String email) throws Exception {
        if(email == null || !floristMap.containsKey(email)){
            throw new Exception("Florist's Inventory Cannot Be Found");
        }
        return floristMap.get(email).getInventory();
    }


    public int updateHistory(Order order, String email){

        Florist florist = floristMap.get(email);

        florist.getHistory().getHistory().add(order);

        return 0;
    }
    public History getHistory(String email){
        return floristMap.get(email).getHistory();
    }


    public int addOrder(Order order) {
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
}
