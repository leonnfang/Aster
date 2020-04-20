package com.Aster.Service;

import com.Aster.Database.FloristRepository;
import com.Aster.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloristService {
    private FloristRepository floristRepsoitory;
    @Autowired
    public FloristService(FloristRepository floristRepsoitory) {
        this.floristRepsoitory = floristRepsoitory;
    }

    public boolean addFlorist(Florist florist) throws Exception {
        if(florist == null){
            throw new Exception("Invalid Florist Input");
        }
        return floristRepsoitory.addFlorist(florist);
    }
    public boolean deleteFlorist(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid Email address");
        }
        if(!floristRepsoitory.deleteFlorist(email)){
            throw new Exception("Cannot delete the florist");
        }else{
            return true;
        }
    }
    public String getUser_name(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid email address");
        }
        return floristRepsoitory.getUser_name(email);
    }
    public Florist getFlorist(String email) {
        return floristRepsoitory.getFlorist(email);
    }


    public boolean addProduct(String email, Product product, int quantity) throws Exception{
        return floristRepsoitory.addProduct(email, product, quantity);
    }
    public boolean removeProduct(String email, Product product) throws Exception{
        return floristRepsoitory.removeProduct(email, product);
    }
    public boolean updateInventory(String floristEmail, Product product, int quantity) throws Exception {
        return floristRepsoitory.updateInventory(floristEmail,product,quantity);
    }
    public Inventory viewInventory(String email) throws Exception {
        return floristRepsoitory.viewInventory(email);
    }


    public History getHistory(String email) {
        return floristRepsoitory.getHistory(email);
    }
}
