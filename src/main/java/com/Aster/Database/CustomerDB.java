package com.Aster.Database;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB {
    Map<String, Customer> customerMap = new HashMap<>();

    public void addUser(Customer customer) {
    }

    public String getUsername(String email) {
        return "";
    }

    public int deleteUser(String email) {
        return 0;
    }

    public int addOrder(Order order) {
        return 0;
    }
}
