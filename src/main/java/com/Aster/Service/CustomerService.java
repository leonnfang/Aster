package com.Aster.Service;

import com.Aster.Database.CustomerDB;
import com.Aster.Database.FloristDB;
import com.Aster.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
public class CustomerService {
    private CustomerDB customerDB;
    private FloristDB floristDB;

    @Autowired
    public CustomerService(CustomerDB customerDB, FloristDB floristDB){
        this.customerDB = customerDB;
        this.floristDB = floristDB;
    }

    public boolean addCustomer(Customer customer) throws Exception{
        return customerDB.addCustomer(customer);
    }
    public Customer getCustomer(String email) throws Exception{
        return customerDB.getCustomer(email);
    }
    public boolean deleteCustomer(String email) throws Exception{
        return customerDB.deleteCustomer(email);
    }


    public boolean addCart(String email, Order order) throws Exception{
        if(!customerDB.isValid(email)){
            throw new Exception("Email Does Not Exists");
        }
        if(!floristDB.isvalid((order.getFloristEmail()))){
            throw new Exception("Florist Does Not Exists");
        }

        String productName = order.getProductName();
        Florist florist = floristDB.getFlorist(order.getFloristEmail());
        Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
        if(!inventoryMap.containsKey(productName)){
            throw new Exception("Such Product Does Not Exists");
        }

        int quantityLeft = (int) inventoryMap.get(productName).lastElement();
        if(quantityLeft < order.getQuantity()){
            throw new Exception("Not Enough Quantity in Inventory");
        }

        Customer customer = customerDB.getCustomer(email);
        Cart cart = customer.getCart();
        Product product = (Product) inventoryMap.get(productName).firstElement();

        if(customerDB.isInCart(email, order)){
            if(quantityLeft < customerDB.getQuantity(email, order)){
                throw new Exception("Not Enough Quantity in Inventory, Cannot Add That Much Quantity");
            }
            double curPrice = cart.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * order.getQuantity();
            cart.setTotalprice(curPrice + addPrice);

            return customerDB.updateCart(email, order);
        }
        else{
            double curPrice = cart.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * order.getQuantity();
            cart.setTotalprice(curPrice + addPrice);

            return customerDB.addCart(email, order);
        }
    }
    public boolean removeCart(String email, String orderID) throws Exception{
        if(!customerDB.isValid(email)){
            throw new Exception("Email Does Not Exist");
        }
        Customer customer = customerDB.getCustomer(email);
        Cart cart = customer.getCart();
        List<Order> cartList = cart.getCartList();
        double curPrice = cart.getTotalprice();

        for(Order cur_order : cartList){
            if(cur_order.getId().equals(orderID)){
                String productName = cur_order.getProductName();
                Florist florist = floristDB.getFlorist(cur_order.getFloristEmail());
                Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
                Product product = (Product) inventoryMap.get(productName).firstElement();
                double productPrice = product.getPrice();

                double cancelPrice = productPrice * cur_order.getQuantity();
                cart.setTotalprice(curPrice - cancelPrice);
            }
        }

        return customerDB.removeCart(email, orderID);
    }
    public Cart viewCart(String email) throws Exception{
        if(customerDB.isValid(email)) {
            return customerDB.viewCart(email);
        }
        else throw new Exception("Email does not exist");
    }
    public boolean emptyCart(String email) throws Exception{
        if(customerDB.isValid(email)) {
            return customerDB.emptyCart(email);
        }
        else throw new Exception("Email does not exist");
    }
    //TODO might need to make editCart
    public int editCart(String email, Order order) throws Exception{
        return 0;
    }

    //TODO have to add 'order cancellation'
    //TODO finish checkout 'Florist's update history'
    public boolean checkout(String email) throws Exception{
        if(customerDB.isValid(email)) {
            //last check of cart
            customerDB.viewCart(email);
            //make payment

            //send order to florist & update history both
            List<Order> cur_cart = customerDB.updateHistory(email);
            for(Order order : cur_cart){
                floristDB.updateHistory(order, order.getFloristEmail());
            }
            //empty cart
            customerDB.emptyCart(email);
            return true;
        }
        else throw new Exception("Email does not exist");
    }
    public List<Order> viewHistory(String email) throws Exception{
        if(customerDB.isValid(email)) {
            return customerDB.viewHistory(email);
        }
        else throw new Exception("Email does not exist");
    }
}
