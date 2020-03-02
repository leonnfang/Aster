package com.Aster.Service;

import com.Aster.Database.FloristDB;
import com.Aster.Model.Florist;
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
        floristDB.addFlorist(florist);
        return 0;
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
    public int updateInventory(Florist florist, Product product, int quantity) throws Exception {
        System.out.println("Updating the Inventory");
        if(florist == null || product == null){
            throw new Exception("Invalid product or florist information");
        }
        return floristDB.updateInventory(florist,product,quantity);
    }
}
