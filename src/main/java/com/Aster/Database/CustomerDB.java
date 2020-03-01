package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB extends UserDB {
    @Override
    public int addOrder(Order order){

        return 0;
    }
    @Override
    public int cancelOrder(Order order){

        return 0;
    }
    @Override
    public History getHistory(User user){

        return user.getHistory();
    }
    @Override
    public Inventory getInventory(Florist florist){

        return florist.getInventory();
    }
    @Override
    public List<Product> getProduct(){
        return new ArrayList<Product>();
    }

}
