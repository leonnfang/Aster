package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB {
    Map<String,Customer> customerMap = new HashMap<>();

    public int addCustomer(Customer customer) throws Exception {
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(customerMap.containsKey(customer.getEmail())){
            throw new Exception("User already exits");
        }

        customerMap.put(customer.getEmail(), customer);
        System.out.println("Customer was created successfully");
        System.out.println(customer.getEmail());
        return 0;
    }
    public String getCustomername(String email) throws Exception{
        if(email == null){
            throw new Exception("It is not a valid email");
        }
        else if(!customerMap.containsKey(email)){
            System.out.println("checking if the email exists: " + email);
            throw new Exception("Email dose not exist");
        }
        else{
            return customerMap.get(email).getUser_name();
        }
    }
    public int deleteCustomer(String email) throws Exception {
        if (email == null) {
            throw new Exception("It is not a valid email");
        } else if (!customerMap.containsKey(email)) {
            System.out.println("checking if the email exists: " + email);
            throw new Exception("Email dose not exist");
        } else {
            customerMap.remove(email);
            System.out.println("The account was deleted successively");
            return 0;
        }
    }


    public int addOrder(Order order){
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }


    public int addCart(String email, Order order){
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        cur_cart.add(order);
        return 0;
    }
    public int removeCart(String email, Order order){
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        if(!cur_cart.contains(order)){
            System.out.println("Such Order does not exist in your cart");
            return 1;
        }
        else cur_cart.remove(order);
        return 0;
    }
    public int emptyCart(String email){
        Customer customer = customerMap.get(email);
        customer.getCart().getCartList().clear();
        return 0;
    }
    public List<Order> viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        for(Order order : cur_cart){
            System.out.println(order.getFlorist().getEmail() + "-------->" + order.getCustomer().getEmail());
            System.out.println(order.getProduct().getName() + "(" + order.getQuantity() + ")");
        }
        return cur_cart;
    }



}
