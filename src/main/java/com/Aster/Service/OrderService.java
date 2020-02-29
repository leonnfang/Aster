package com.Aster.Service;

import com.Aster.Database.CustomerDB;
import com.Aster.Database.FloristDB;
import com.Aster.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private FloristDB floristDB;
    private CustomerDB customerDB;
    @Autowired
    OrderService(FloristDB floristDB, CustomerDB customerDB) {
        this.floristDB = floristDB;
        this.customerDB = customerDB;
    }

    public int addOrder(Order order) throws Exception {
        if(floristDB.addOrder(order) != 0 || customerDB.addOrder(order) != 0){
            throw new Exception("Cannot place the order");
        }
        return 0;
    }
}
