package com.Aster.Database;

import com.Aster.Model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@Primary
@Repository
public class JdbiFloristRepository implements FloristRepository {
    @Override
    public boolean addFlorist(Florist florist) throws Exception {
        return false;
    }

    @Override
    public boolean deleteFlorist(String email) throws Exception {
        return false;
    }

    @Override
    public Florist getFlorist(String email) {
        return null;
    }

    @Override
    public String getUser_name(String email) throws Exception {
        return null;
    }

    @Override
    public boolean isvalid(String email) {
        return false;
    }

    @Override
    public boolean addProduct(String email, Product product, int quantity) throws Exception {
        return false;
    }

    @Override
    public boolean removeProduct(String email, Product product) throws Exception {
        return false;
    }

    @Override
    public boolean updateInventory(String floristEmail, Product product, int quantity) throws Exception {
        return false;
    }

    @Override
    public Inventory viewInventory(String email) throws Exception {
        return null;
    }

    @Override
    public int updateHistory(Order order, String email) {
        return 0;
    }

    @Override
    public History getHistory(String email) {
        return null;
    }

    @Override
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public int cancelOrder(Order order) {
        return 0;
    }
}
