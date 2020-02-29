package com.Aster.Database;
import java.util.*;

import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public class UserDB {
    Map<String,User> UserMap = new HashMap<>();

    public int addUser(User user) throws Exception {
        if(UserMap.containsKey(user.getEmail())){
            throw new Exception("User already exits");
        }
        UserMap.put(user.getEmail(), user);
        if(user != UserMap.get(user.getEmail())){
            System.out.println("User was not created successfully");
            return 1;
        }
        System.out.println("User was created successfully");
        System.out.println(user.getEmail());
        return 0;
    }
    public String getUsername(String email) throws Exception{
        if(email == null){
            throw new Exception("It is not a valid email");
        }
        else if(!UserMap.containsKey(email)){
            System.out.println("checking if the email exits or not"+email);
            throw new Exception("Email dose not exist");
        }
        else{
            return UserMap.get(email).getUsername();
        }
    }
    public int deleteUser(String email) throws Exception {
        if (email == null) {
            throw new Exception("It is not a valid email");
        } else if (!UserMap.containsKey(email)) {
            System.out.println("checking if the email exits or not" + email);
            throw new Exception("Email dose not exist");
        } else {
            UserMap.remove(email);
            System.out.println("The account was deleted successively");
            return 0;
        }
    }

    public int addOrder(Order order){
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
    public History getHistory(User user){
        return user.getHistory();
    }
    public Inventory getInventory(Florist florist){
        return florist.getInventory();
    }
    public Emaillist getEmaillist(){
        return new Emaillist();
    }
    public List<Product> getProduct(){
        return new ArrayList<Product>();
    }

}
