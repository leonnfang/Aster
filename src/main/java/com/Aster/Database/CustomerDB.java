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
        List<Order> newHistoryList = new ArrayList<>();
        List<Order> newCartList = new ArrayList<>();

        History newHistory = new History(newHistoryList);
        Cart newCart = new Cart(newCartList, 0);
        Customer newCustomer = new Customer(customer.getUser_name(), customer.getPassword(),
                                            customer.getEmail(), customer.getLastName(),
                                            customer.getFirstName(), customer.getAddress(),
                                            newHistory, newCart);

        customerMap.put(newCustomer.getEmail(), newCustomer);
        return 0;
    }
    public Customer getCustomer(String email) throws Exception{
        if(email == null){
            throw new Exception("It is not a valid email");
        }
        else if(!customerMap.containsKey(email)){
            throw new Exception("Email dose not exist");
        }
        else{
            return customerMap.get(email);
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
        List<Order> cart = customer.getCart().getCartList();
        String orderID = UUID.randomUUID().toString();
        Order neworder = new Order(order.getFloristEmail(), order.getCustomerEmail(), order.getDate(),
                                    order.getProductName(), order.getQuantity(), false, orderID);
        cart.add(neworder);
        return 0;
    }
    public int removeCart(String email, String orderID) throws Exception{
        if(orderID == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cart = customer.getCart().getCartList();

        for(Order cur_order : cart){
            if(cur_order.getId().equals(orderID)){
                cart.remove(cur_order);
                return 0;
            }
        }
        throw new Exception("Such Order does not exist");
    }
    public int emptyCart(String email) throws Exception{
        Customer customer = customerMap.get(email);
        List<Order> cart = customer.getCart().getCartList();
        if(!cart.isEmpty()) {
            cart.clear();
        }
        else throw new Exception("Cart is already Empty");
        return 0;
    }
    public List<Order> viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        for(Order order : cur_cart){
            System.out.println(order.getFloristEmail() + "-------->" + order.getCustomerEmail());
            System.out.println(order.getProductName() + "(" + order.getQuantity() + ")");
        }
        return cur_cart;
    }


    public List<Order> viewHistory(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_history = customer.getHistory().getHistory();
        for(Order order : cur_history){
            System.out.println(order.getCustomerEmail() + "-------->" + order.getFloristEmail());
            System.out.println(order.getProductName() + "(" + order.getQuantity() + ")");
        }
        return cur_history;
    }
    public int updateHistory(String email){
        Customer customer = customerMap.get(email);
        for(Order order : customer.getCart().getCartList()){
            customer.getHistory().getHistory().add(order);
        }
        return 0;
    }

    public int addOrder(Order order){
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
}
