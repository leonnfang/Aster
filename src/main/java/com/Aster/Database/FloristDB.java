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
        Inventory newInventory = new Inventory(new HashMap<>(),true,0);
        History newHistory = new History(new ArrayList<>());
        Florist newflorist = new Florist(florist.getUser_name(),
                florist.getPassword(),
                florist.getEmail(),
                florist.getLastName(),
                florist.getFirstName(),
                florist.getAddress(),
                newInventory,
                newHistory);

        floristMap.put(florist.getEmail(),newflorist);
        System.out.println("user was added");
        return 0;
    }
    public int deleteFlorist(String email) throws Exception {
        if (!floristMap.containsKey(email)){
            throw new Exception("Florist Dose not Exist");
        }
        floristMap.remove(email);
        if(!floristMap.containsKey(email)){
            System.out.println("user was deleted");
            return 0;
        }
        return 1;
    }
    public String getUser_name(String email) throws Exception {
        if(!floristMap.containsKey(email)){
            throw new Exception("Invalid Email Address(Dose Not Exist)");
        }
        return floristMap.get(email).getUser_name();
    }

    public Map<String,Integer> getProduct(String email){
        return floristMap.get(email).getInventory().getInventoryMap();
    }

    public int updateInventory(String floristEmail, String productName, int quantity) throws Exception {
        Florist florist = floristMap.get(floristEmail);
        Inventory floristInventory = florist.getInventory();
        Map<String,Integer> inventoryMap = floristInventory.getInventoryMap();
        int numberLeft = inventoryMap.get(productName);
        if(!inventoryMap.containsKey(productName)){
            System.out.println("Flowers Dose not Exist Before" );
            System.out.println("A New Product Will Be Created");
            if(quantity < 0){
                throw new Exception("Number of Flowers Cannot Not Be Zero");
            }
            inventoryMap.put(productName,quantity);
            return 0;
        }
        if(numberLeft + quantity < 0){
            throw new Exception("Not Enough Flowers Left");
        }
        inventoryMap.put(productName,quantity);
        return 0;
    }

    public Inventory getInventory(String email) throws Exception {
        if(email == null || !floristMap.containsKey(email)){
            throw new Exception("Florist's Inventory Cannot Be Found");
        }
        return floristMap.get(email).getInventory();
    }
    public int updateHistory(Order order){
        return 0;
    }
    public History getHistory(String email){
        return floristMap.get(email).getHistory();
    }

    public Florist getFlorist(String email) {
        return floristMap.get(email);
    }


    public int addOrder(Order order) {
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }

    public int addProduct(String email, String productName, String floristName, String description) {
        Product newproduct = new Product(productName,0,description,floristName);
        floristMap.get(email).getInventory().getInventoryMap().put(productName,0);
        return 0;
    }
}
