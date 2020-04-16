package com.Aster.Database;
import com.Aster.Model.*;

import org.springframework.stereotype.Repository;

import java.security.KeyPair;
import java.util.*;
@Repository
public class FloristDB{
    Map<String,Florist> floristMap = new HashMap<>();

    public boolean addFlorist(Florist florist) throws Exception {
        if(floristMap.containsKey(florist.getEmail())){
            throw new Exception("This Florist Has Already Existed");
        }

        List<Order> newHistoryList = new ArrayList<>();
        Map<String, Vector> newInventoryMap = new HashMap<>();

        Inventory newInventory = new Inventory(newInventoryMap,true,0);
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
        return true;
    }
    public boolean deleteFlorist(String email) throws Exception {
        if (!floristMap.containsKey(email)){
            throw new Exception("Florist Dose not Exist");
        }
        floristMap.remove(email);
        if(!floristMap.containsKey(email)){
            return true;
        }
        return false;
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


    public boolean addProduct(String email, Product product, int quantity) throws Exception{
        if(quantity < 0){
            throw new Exception("Number of Product Cannot be Lower than Zero");
        }
        Product newProduct = new Product(product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStoreName());
        Vector v = new Vector(2);
        v.add(newProduct);
        v.add(quantity);

        Florist florist = floristMap.get(email);
        Inventory floristInventory = florist.getInventory();
        floristInventory.getInventoryMap().put(product.getName(), v);

        floristInventory.setTotalNumber(floristInventory.getTotalNumber() + quantity);

        return true;
    }
    public boolean removeProduct(String email, Product product) throws Exception{
        Florist florist = floristMap.get(email);
        Inventory floristInventory = florist.getInventory();
        Map<String,Vector> inventoryMap = floristInventory.getInventoryMap();
        int num = (int) inventoryMap.get(product.getName()).lastElement();

        if(!inventoryMap.containsKey(product.getName())){
            throw new Exception("Such Product Does Not Exist");
        }

        inventoryMap.remove(product.getName());

        floristInventory.setTotalNumber(floristInventory.getTotalNumber() - num);

        return true;
    }
    public boolean updateInventory(String floristEmail, Product product, int quantity) throws Exception {
        Florist florist = floristMap.get(floristEmail);
        Inventory floristInventory = florist.getInventory();
        Map<String,Vector> inventoryMap = floristInventory.getInventoryMap();
        int numberLeft = (int) inventoryMap.get(product.getName()).lastElement();

        if(!inventoryMap.containsKey(product.getName())){
            throw new Exception("Such Product Does Not Exist");
        }
        if(numberLeft + quantity < 0){
            throw new Exception("Not Enough Products Left");
        }

        Product newProduct = new Product(product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStoreName());
        Vector v = new Vector(2);
        v.add(newProduct);
        v.add(numberLeft + quantity);
        inventoryMap.put(product.getName(), v);

        floristInventory.setTotalNumber(floristInventory.getTotalNumber() + quantity);
        return true;
    }
    public Inventory viewInventory(String email) throws Exception {
        if(email == null || !floristMap.containsKey(email)){
            throw new Exception("Florist's Inventory Cannot Be Found");
        }
        return floristMap.get(email).getInventory();
    }

    //public double checkPrice(){}

    public int updateHistory(Order order, String email){

        Florist florist = floristMap.get(email);

        florist.getHistory().getHistory().add(order);

        return 0;
    }
    public History getHistory(String email){
        return floristMap.get(email).getHistory();
    }

    //TODO confirm order: will reduce quantity in inventory

    public int addOrder(Order order) {
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
}
