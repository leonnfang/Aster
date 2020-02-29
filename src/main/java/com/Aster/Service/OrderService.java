package com.Aster.Service;
import com.Aster.Database.FloristDB;
import com.Aster.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private FloristDB floristdb;
    @Autowired
    public OrderService(FloristDB floristdb) {
        this.floristdb = floristdb;
    }

    public void makeOrder(Order order){
        floristdb.addOrder();
    }
}
