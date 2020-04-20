package com.Aster.Database;
import com.Aster.Model.*;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Primary
@Repository
public class FloristRepositoryInMemory implements FloristRepository {
    Map<String,Florist> floristMap = new HashMap<>();

    @Override
    public boolean addFlorist(Florist florist) throws Exception {
        if(floristMap.containsKey(florist.getEmail())){
            throw new Exception("This Florist Has Already Existed");
        }

        List<Purchase> newHistory = new ArrayList<>();
        Map<String, Vector> newInventoryMap = new HashMap<>();

        Inventory newInventory = new Inventory(newInventoryMap,true,0);

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
    @Override
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
    @Override
    public Florist getFlorist(String email) {
        return floristMap.get(email);
    }
    @Override
    public String getUser_name(String email) throws Exception {
        if(!floristMap.containsKey(email)){
            throw new Exception("Invalid Email Address(Dose Not Exist)");
        }
        return floristMap.get(email).getUser_name();
    }
    @Override
    public boolean isvalid(String email){
        if(floristMap.containsKey(email)) return true;
        else return false;
    }


    @Override
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
    @Override
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
    @Override
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
    @Override
    public Inventory viewInventory(String email) throws Exception {
        if(email == null || !floristMap.containsKey(email)){
            throw new Exception("Florist's Inventory Cannot Be Found");
        }
        return floristMap.get(email).getInventory();
    }

    //public double checkPrice(){}

    @Override
    public int updateHistory(Purchase purchase, String email){

        Florist florist = floristMap.get(email);

        florist.getHistory().add(purchase);

        return 0;
    }
    @Override
    public List<Purchase> getHistory(String email){
        return floristMap.get(email).getHistory();
    }

    //TODO confirm order: will reduce quantity in inventory

    @Override
    public int addOrder(Purchase purchase) {
        return 0;
    }
    @Override
    public int cancelOrder(Purchase purchase){
        return 0;
    }
}
