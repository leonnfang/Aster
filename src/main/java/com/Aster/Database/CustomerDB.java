package com.Aster.Database;
import com.Aster.Model.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerDB extends UserDB {
    Map<String,Customer> customerMap = new HashMap<>();

    public int addCostomer(Customer customer) throws Exception {
        if(customer == null || customerMap.containsKey(customer.getEmail())){
            throw new Exception("Invalid customer");
        }
        customerMap.put(customer.getEmail(),customer);
        System.out.println("Florist was created successfully");
        return 0;
    }

    public int addtoCart(String email, Order order){
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        cur_cart.add(order);
        return 0;
    }

    public int removefromCart(String email, Order order){
        if(order == null) {
            throw new NullPointerException(("order pointer is null"));
        }
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        cur_cart.remove(order);
        return 0;
    }

    public int emptyCart(String email){
        Customer customer = customerMap.get(email);
        customer.getCart().getCartList().clear();
        return 0;
    }

    public void viewCart(String email){
        Customer customer = customerMap.get(email);
        List<Order> cur_cart = customer.getCart().getCartList();
        for(Order order : cur_cart){
            System.out.println(order.getFlorist().getEmail() + "-------->" + order.getCustomer().getEmail());
            System.out.println(order.getProduct().getName() + "(" + order.getQuantity() + ")");
        }
    }

    @Override
    public int addOrder(Order order){
        if(order == null){
            throw new NullPointerException("order pointer is null");
        }

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
