package com.Aster.Service;

import com.Aster.Database.FloristDB;
import com.Aster.Model.Florist;
import com.Aster.Model.History;
import com.Aster.Model.Inventory;
import com.Aster.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloristService {
    private FloristDB floristDB;
    @Autowired
    public FloristService(FloristDB floristDB) {
        this.floristDB = floristDB;
    }

    public boolean addFlorist(Florist florist) throws Exception {
        if(florist == null){
            throw new Exception("Invalid Florist Input");
        }
        return floristDB.addFlorist(florist);
    }
    public boolean deleteFlorist(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid Email address");
        }
        if(!floristDB.deleteFlorist(email)){
            throw new Exception("Cannot delete the florist");
        }else{
            return true;
        }
    }
    public String getUser_name(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid email address");
        }
        return floristDB.getUser_name(email);
    }
    public Florist getFlorist(String email) {
        return floristDB.getFlorist(email);
    }


    public boolean addProduct(String email, Product product, int quantity) throws Exception{
        return floristDB.addProduct(email, product, quantity);
    }
    public boolean updateInventory(String floristEmail, Product product, int quantity) throws Exception {
        return floristDB.updateInventory(floristEmail,product,quantity);
    }
    public Inventory viewInventory(String email) throws Exception {
        return floristDB.viewInventory(email);
    }


    public History getHistory(String email) {
        return floristDB.getHistory(email);
    }
}
