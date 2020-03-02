package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FloristDB{
    Map<String,Florist> floristMap = new HashMap<>();
    public void addFlorist(Florist florist) throws Exception {
        if(floristMap.containsKey(florist.getEmail())){
            throw new Exception("florist has already existed");
        }
        floristMap.put(florist.getEmail(),florist);
    }
    public int deleteFlorist(){
        return 0;
    }

    public String getUser_name(String email){
        return "";
    }

    public int addOrder(Order order) {
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }

    public History getHistory(User user){
        return new History();
    }
    public int updateInventory(){
        return 0;
    }
    public Emaillist getEmaillist(){
        return new Emaillist();
    }
    public List<Product> getProduct(){
        return new ArrayList<Product>();
    }

}
