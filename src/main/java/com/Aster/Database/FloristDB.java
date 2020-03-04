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
        floristMap.put(florist.getEmail(),florist);
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
    public String getUser_name(String email) throws Exception {
        if(!floristMap.containsKey(email)){
            throw new Exception("Invalid Email Address(Dose Not Exist)");
        }
        return floristMap.get(email).getUser_name();
    }

    public int addOrder(Order order) {
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }

    public History getHistory(String email){
        return floristMap.get(email).getHistory();
    }
    public Emaillist getEmaillist(){
        return new Emaillist();
    }
    public List<Product> getProduct(){
        return new ArrayList<Product>();
    }

    public int updateInventory(String floristEmail, String productName, int quantity) {
        return 0;
    }

    public int initInventory(String email) {
        return 0;
    }

    public Florist getFlorist(String email) {
        return floristMap.get(email);
    }
}
