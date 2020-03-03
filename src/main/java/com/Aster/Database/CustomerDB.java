package com.Aster.Database;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB {
    Map<String, Customer> customerMap = new HashMap<>();

    public int addCustomer(Customer customer) {
        return 0;
    }

    public String getCustomername(String email) {
        return null;
    }

    public int deleteCustomer(String email) {
        return 0;
    }

    public List<Order> viewHistory(String email) {
        Customer customer = customerMap.get(email);
        List<Order> cur_history = customer.getHistory().getHistory();
        for (Order order : cur_history) {
            System.out.println(order.getFlorist().getEmail() + "-------->" + order.getCustomer().getEmail());
            System.out.println(order.getProduct().getName() + "(" + order.getQuantity() + ")");
        }
        return cur_history;
    }

    public int addCart(String email, Order order) {
        return 0;
    }

    public List<Order> viewCart(String email) {
        return null;
    }

    public int removeCart(String email, Order order) {
        return 0;
    }

    public int emptyCart(String email) {
        return 0;
    }

    public int addOrder(Order order) {
        return 0;
    }

    public int cancelOrder(Order order) {
        return 0;
    }
}
