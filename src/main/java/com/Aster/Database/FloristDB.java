package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FloristDB extends UserDB {
    @Override
    public int addUser(User user) throws Exception {
        System.out.println("in db layer");
        return super.addUser(user);
    }

    @Override
    public int deleteUser(String email) throws Exception {
        return super.deleteUser(email);
    }

    @Override
    public int addOrder(Order order) {
        String customerEmail = order.getFlorist().getEmail();

        return super.addOrder(order);
    }

    @Override
    public int cancelOrder(Order order) {
        return super.cancelOrder(order);
    }

    @Override
    public History getHistory(User user) {
        return super.getHistory(user);
    }

    @Override
    public Inventory getInventory(Florist florist) {
        return super.getInventory(florist);
    }

    @Override
    public List<Product> getProduct() {
        return super.getProduct();
    }
}
