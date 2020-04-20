package com.Aster.Service;

import com.Aster.Database.CustomerRepository;
import com.Aster.Database.FloristRepository;
import com.Aster.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    private FloristRepository floristRepsoitory;


    @Autowired
    public CustomerService(CustomerRepository customerRepository, FloristRepository floristRepsoitory){
        this.customerRepository = customerRepository;
        this.floristRepsoitory = floristRepsoitory;
    }

    public boolean addCustomer(Customer customer) throws Exception{
        return customerRepository.addCustomer(customer);
    }
    public Customer getCustomer(String email) throws Exception{
        return customerRepository.getCustomer(email);
    }
    public boolean deleteCustomer(String email) throws Exception{
        return customerRepository.deleteCustomer(email);
    }


    public boolean addCart(String email, Order order) throws Exception{
        if(!customerRepository.isValid(email)){
            throw new Exception("Email Does Not Exists");
        }
        if(!floristRepsoitory.isvalid((order.getFloristEmail()))){
            throw new Exception("Florist Does Not Exists");
        }

        String productName = order.getProductName();
        Florist florist = floristRepsoitory.getFlorist(order.getFloristEmail());
        Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
        if(!inventoryMap.containsKey(productName)){
            throw new Exception("Such Product Does Not Exists");
        }

        int quantityLeft = (int) inventoryMap.get(productName).lastElement();
        if(quantityLeft < order.getQuantity()){
            throw new Exception("Not Enough Quantity in Inventory");
        }

        Customer customer = customerRepository.getCustomer(email);
        Profile profile = customer.getProfile();
        Product product = (Product) inventoryMap.get(productName).firstElement();

        if(customerRepository.isInCart(email, order)){
            if(quantityLeft < customerRepository.getQuantity(email, order)){
                throw new Exception("Not Enough Quantity in Inventory, Cannot Add That Much Quantity");
            }
            double curPrice = profile.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * order.getQuantity();
            profile.setTotalprice(curPrice + addPrice);

            return customerRepository.updateCart(email, order);
        }
        else{
            double curPrice = profile.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * order.getQuantity();
            profile.setTotalprice(curPrice + addPrice);

            return customerRepository.addCart(email, order);
        }
    }
    public boolean removeCart(String email, String orderID) throws Exception{
        if(!customerRepository.isValid(email)){
            throw new Exception("Email Does Not Exist");
        }
        Customer customer = customerRepository.getCustomer(email);
        Profile profile = customer.getProfile();
        List<Order> cartList = profile.getCart();
        double curPrice = profile.getTotalprice();

        for(Order cur_order : cartList){
            if(cur_order.getId().equals(orderID)){
                String productName = cur_order.getProductName();
                Florist florist = floristRepsoitory.getFlorist(cur_order.getFloristEmail());
                Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
                Product product = (Product) inventoryMap.get(productName).firstElement();
                double productPrice = product.getPrice();

                double cancelPrice = productPrice * cur_order.getQuantity();
                profile.setTotalprice(curPrice - cancelPrice);
            }
        }

        return customerRepository.removeCart(email, orderID);
    }
    public List<Order> viewCart(String email) throws Exception{
        if(customerRepository.isValid(email)) {
            return customerRepository.viewCart(email);
        }
        else throw new Exception("Email does not exist");
    }
    public boolean emptyCart(String email) throws Exception{
        if(customerRepository.isValid(email)) {
            return customerRepository.emptyCart(email);
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
        if(customerRepository.isValid(email)) {
            //last check of cart
            customerRepository.viewCart(email);
            //make payment

            //send order to florist & update history both
            List<Order> cur_cart = customerRepository.updateHistory(email);
            for(Order order : cur_cart){
                floristRepsoitory.updateHistory(order, order.getFloristEmail());
            }
            //empty cart
            customerRepository.emptyCart(email);
            return true;
        }
        else throw new Exception("Email does not exist");
    }
    public List<Order> viewHistory(String email) throws Exception{
        if(customerRepository.isValid(email)) {
            return customerRepository.viewHistory(email);
        }
        else throw new Exception("Email does not exist");
    }
}
