package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB {
    Map<String,Customer> customerMap = new HashMap<>();

    public int addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(customerMap.containsKey(customer.getEmail())){
            throw new Exception("Customer already exits");
        }
        History newhistory = new History();
        Cart newcart = new Cart();
        Customer newCustomer = new Customer(customer.getUser_name(), customer.getPassword(),
                                            customer.getEmail(), customer.getLastName(),
                                            customer.getFirstName(), customer.getAddress(),
                                            newhistory, newcart);

        customerMap.put(newCustomer.getEmail(), newCustomer);
        return 0;
    }
    public String getCustomername(String email) throws Exception{
        if(email == null){
            throw new Exception("It is not a valid email");
        }
        else if(!customerMap.containsKey(email)){
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
            throw new Exception("Email dose not exist");
        } else {
            customerMap.remove(email);
            return 0;
        }
    }
    public boolean isvalid(String email){
        if(customerMap.containsKey(email)) return true;
        else return false;
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
        List<Order> cart = customer.getCart().getCartList();
        System.out.println("1111");
        if(!cart.isEmpty()) {
            System.out.println("33333");
            cart.clear();
            System.out.println("44444");
        }
        System.out.println("2222");
        return 0;
    }
    public List<Order> viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        for(Order order : cur_cart){
            System.out.println(order.getFloristEmail() + "-------->" + order.getCustomerEmail());
            System.out.println(order.getProductName().getName() + "(" + order.getQuantity() + ")");
        }
        return cur_cart;
    }


    public List<Order> viewHistory(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_history = customer.getHistory().getHistory();
        for(Order order : cur_history){
            System.out.println(order.getFloristEmail() + "-------->" + order.getCustomerEmail());
            System.out.println(order.getProductName().getName() + "(" + order.getQuantity() + ")");
        }
        return cur_history;
    }
    public int updateHistory(String email, int opt){
        return 0;
    }

    public int addOrder(Order order){
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
}
