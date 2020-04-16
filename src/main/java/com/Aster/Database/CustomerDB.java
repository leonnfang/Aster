package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB {
    Map<String,Customer> customerMap = new HashMap<>();


    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(customerMap.containsKey(customer.getEmail())){
            throw new Exception("Customer Already Exists");
        }
        List<Order> newHistoryList = new ArrayList<>();
        List<Order> newCartList = new ArrayList<>();

        History newHistory = new History(newHistoryList);
        Cart newCart = new Cart(newCartList, 0);
        Customer newCustomer = new Customer(customer.getUser_name(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getAddress(),
                newHistory,
                newCart);

        customerMap.put(newCustomer.getEmail(), newCustomer);
        return true;
    }
    public Customer getCustomer(String email) throws Exception{
        if(email == null){
            throw new Exception("Invalid Email");
        }
        else if(!customerMap.containsKey(email)){
            throw new Exception("Email Dose Not Exist");
        }
        else{
            return customerMap.get(email);
        }
    }
    public boolean deleteCustomer(String email) throws Exception {
        if (email == null) {
            throw new Exception("Invalid Email");
        } else if (!customerMap.containsKey(email)) {
            throw new Exception("Email Dose Not Exist");
        } else {
            customerMap.remove(email);
            return true;
        }
    }
    public boolean isValid(String email){
        if(customerMap.containsKey(email)) return true;
        else return false;
    }

    
    public boolean addCart(String email, Order order) throws Exception{
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        Cart cart = customer.getCart();
        List<Order> cartList = cart.getCartList();
        String orderID = UUID.randomUUID().toString();
        Order newOrder = new Order(order.getFloristEmail(),
                order.getCustomerEmail(),
                order.getDate(),
                order.getProductName(),
                order.getQuantity(),
                false,
                orderID);
        cartList.add(newOrder);
        return true;
    }
    public boolean removeCart(String email, String orderID) throws Exception{
        if(orderID == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cartList = customer.getCart().getCartList();

        for(Order cur_order : cartList){
            if(cur_order.getId().equals(orderID)){
                cartList.remove(cur_order);
                return true;
            }
        }
        throw new Exception("Such Order does not exist");
    }
    public boolean emptyCart(String email) throws Exception{
        Customer customer = customerMap.get(email);
        Cart cart = customer.getCart();
        List<Order> cartList = customer.getCart().getCartList();

        if(!cartList.isEmpty()) {
            cartList.clear();
        }
        else throw new Exception("Cart is already Empty");

        cart.setTotalprice(0);

        return true;
    }
    public Cart viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        return customer.getCart();
    }


    public boolean isInCart(String email, Order order) throws Exception{
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cartList = customer.getCart().getCartList();

        for(Order cur_order : cartList){
            if(cur_order.getFloristEmail().equals(order.getFloristEmail())){
                if(cur_order.getProductName().equals(order.getProductName())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean updateCart(String email, Order order) throws Exception{
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cartList = customer.getCart().getCartList();

        for(Order cur_order : cartList){
            if(cur_order.getFloristEmail().equals(order.getFloristEmail())){
                if(cur_order.getProductName().equals(order.getProductName())){
                    int curQuantity = cur_order.getQuantity();
                    int addQuantity = order.getQuantity();
                    cur_order.setQuantity(curQuantity + addQuantity);
                    return true;
                }
            }
        }
        return false;
    }
    public int getQuantity(String email, Order order) throws Exception{
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cartList = customer.getCart().getCartList();

        for(Order cur_order : cartList){
            if(cur_order.getFloristEmail().equals(order.getFloristEmail())){
                if(cur_order.getProductName().equals(order.getProductName())){
                    int curQuantity = cur_order.getQuantity();
                    int addQuantity = order.getQuantity();
                    return curQuantity + addQuantity;
                }
            }
        }
        return 0;
    }


    public List<Order> viewHistory(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_history = customer.getHistory().getHistory();
        return cur_history;
    }
    public List<Order> updateHistory(String email){
        Customer customer = customerMap.get(email);
        for(Order order : customer.getCart().getCartList()){
            customer.getHistory().getHistory().add(order);
        }
        return customer.getCart().getCartList();
    }


    public int addOrder(Order order){
        return 0;
    }
    public int cancelOrder(Order order){
        return 0;
    }
}
