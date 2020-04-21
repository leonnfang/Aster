package com.Aster.Repository;

import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepositoryInMemory implements CustomerRepository {
    Map<String,Customer> customerMap = new HashMap<>();


    @Override
    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(customerMap.containsKey(customer.getEmail())){
            throw new Exception("Customer Already Exists");
        }
        List<Purchase> newHistory = new ArrayList<>();
        List<Purchase> newCart = new ArrayList<>();

        Cart cart = new Cart(newCart, 0);
        History history = new History(newHistory, true);
        Customer newCustomer = new Customer(customer.getUser_name(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getAddress());

        customerMap.put(newCustomer.getEmail(), newCustomer);
        return true;
    }
    @Override
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
    @Override
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
    @Override
    public boolean isValid(String email){
        if(customerMap.containsKey(email)) return true;
        else return false;
    }

    
    @Override
    public boolean addCart(String email, Purchase purchase) throws Exception{
        if(purchase == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        Cart cart = customer.getCart();
        List<Purchase> cartList = cart.getCart();
        String orderID = UUID.randomUUID().toString();
        Purchase newPurchase = new Purchase(purchase.getFloristEmail(),
                purchase.getCustomerEmail(),
                purchase.getDate(),
                purchase.getProductName(),
                purchase.getQuantity(),
                false,
                orderID);
        cartList.add(newPurchase);
        return true;
    }
    @Override
    public boolean removeCart(String email, String orderID) throws Exception{
        if(orderID == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Purchase> cartList = customer.getCart().getCart();

        for(Purchase cur_purchase : cartList){
            if(cur_purchase.getOrderId().equals(orderID)){
                cartList.remove(cur_purchase);
                return true;
            }
        }
        throw new Exception("Such Order does not exist");
    }
    @Override
    public boolean emptyCart(String email) throws Exception{
        Customer customer = customerMap.get(email);
        Cart cart = customer.getCart();
        List<Purchase> cartList = cart.getCart();

        if(!cartList.isEmpty()) {
            cartList.clear();
        }
        else throw new Exception("Cart is already Empty");

        cart.setTotalprice(0);

        return true;
    }
    @Override
    public List<Purchase> viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Purchase> cur_cart = customer.getCart().getCart();
        return customer.getCart().getCart();
    }


    @Override
    public boolean isInCart(String email, Purchase purchase) throws Exception{
        if(purchase == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Purchase> cartList = customer.getCart().getCart();

        for(Purchase cur_purchase : cartList){
            if(cur_purchase.getFloristEmail().equals(purchase.getFloristEmail())){
                if(cur_purchase.getProductName().equals(purchase.getProductName())){
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean updateCart(String email, Purchase purchase) throws Exception{
        if(purchase == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Purchase> cartList = customer.getCart().getCart();

        for(Purchase cur_purchase : cartList){
            if(cur_purchase.getFloristEmail().equals(purchase.getFloristEmail())){
                if(cur_purchase.getProductName().equals(purchase.getProductName())){
                    int curQuantity = cur_purchase.getQuantity();
                    int addQuantity = purchase.getQuantity();
                    cur_purchase.setQuantity(curQuantity + addQuantity);
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public int getQuantity(String email, Purchase purchase) throws Exception{
        if(purchase == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Purchase> cartList = customer.getCart().getCart();

        for(Purchase cur_purchase : cartList){
            if(cur_purchase.getFloristEmail().equals(purchase.getFloristEmail())){
                if(cur_purchase.getProductName().equals(purchase.getProductName())){
                    int curQuantity = cur_purchase.getQuantity();
                    int addQuantity = purchase.getQuantity();
                    return curQuantity + addQuantity;
                }
            }
        }
        return 0;
    }


    @Override
    public List<Purchase> viewHistory(String email){
        Customer customer = customerMap.get(email);
        List<Purchase> cur_history = customer.getHistory().getHistory();
        return cur_history;
    }
    @Override
    public List<Purchase> updateHistory(String email){
        Customer customer = customerMap.get(email);
        for(Purchase purchase : customer.getCart().getCart()){
            customer.getHistory().getHistory().add(purchase);
        }
        return customer.getCart().getCart();
    }


    @Override
    public int addOrder(Purchase purchase){
        return 0;
    }
    @Override
    public int cancelOrder(Purchase purchase){
        return 0;
    }
}
