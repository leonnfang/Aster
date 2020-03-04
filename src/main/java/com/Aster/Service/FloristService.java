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

    public int addFlorist(Florist florist) throws Exception {
        if(florist == null){
            throw new Exception("Invalid Florist Input");
        }
        return floristDB.addFlorist(florist);
    }
    public int deleteFlorist(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid Email address");
        }
        if(floristDB.deleteFlorist(email) != 0){
            throw new Exception("Cannot delete the florist");
        }else{
            return 0;
        }
    }
    public int updateInventory(String floristEmail, String productName, int quantity) throws Exception {
        System.out.println("Updating the Inventory");
        return floristDB.updateInventory(floristEmail,productName,quantity);
    }

    public String getUser_name(String email) throws Exception {
        if(email == null || email.length() == 0){
            throw new Exception("Invalid email address");
        }
        return floristDB.getUser_name(email);
    }

    public History getHistory(String email) {
        return floristDB.getHistory(email);
    }

    public Florist getFlorist(String email) {
        return floristDB.getFlorist(email);
    }

    public Inventory getInventory(String email) throws Exception {
        return floristDB.getInventory(email);
    }

    public int addProduct(String email, String productName, String floristName, String description) {
        return floristDB.addProduct(email,productName,floristName,description);
    }
}
