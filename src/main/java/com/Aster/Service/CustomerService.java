package com.Aster.Service;

import com.Aster.Repository.CustomerRepository;
import com.Aster.Repository.FloristRepository;
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
    
    public boolean addCart(String email, Purchase purchase) throws Exception{
        if(!customerRepository.isValid(email)){
            throw new Exception("Email Does Not Exists");
        }
        if(!floristRepsoitory.isvalid((purchase.getFloristEmail()))){
            throw new Exception("Florist Does Not Exists");
        }

        String productName = purchase.getProductName();
        Florist florist = floristRepsoitory.getFlorist(purchase.getFloristEmail());
        Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
        if(!inventoryMap.containsKey(productName)){
            throw new Exception("Such Product Does Not Exists");
        }

        int quantityLeft = (int) inventoryMap.get(productName).lastElement();
        if(quantityLeft < purchase.getQuantity()){
            throw new Exception("Not Enough Quantity in Inventory");
        }

        Customer customer = customerRepository.getCustomer(email);
        Cart cart = customer.getCart();
        Product product = (Product) inventoryMap.get(productName).firstElement();

        if(customerRepository.isInCart(email, purchase)){
            if(quantityLeft < customerRepository.getQuantity(email, purchase)){
                throw new Exception("Not Enough Quantity in Inventory, Cannot Add That Much Quantity");
            }
            double curPrice = cart.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * purchase.getQuantity();
            cart.setTotalprice(curPrice + addPrice);

            return customerRepository.updateCart(email, purchase);
        }
        else{
            double curPrice = cart.getTotalprice();
            double productPrice = product.getPrice();
            double addPrice = productPrice * purchase.getQuantity();
            cart.setTotalprice(curPrice + addPrice);

            return customerRepository.addCart(email, purchase);
        }
    }
    public boolean removeCart(String email, String orderID) throws Exception{
        if(!customerRepository.isValid(email)){
            throw new Exception("Email Does Not Exist");
        }
        Customer customer = customerRepository.getCustomer(email);
        Cart cart = customer.getCart();
        List<Purchase> cartList = cart.getCart();
        double curPrice = cart.getTotalprice();

        for(Purchase cur_purchase : cartList){
            if(cur_purchase.getOrderId().equals(orderID)){
                String productName = cur_purchase.getProductName();
                Florist florist = floristRepsoitory.getFlorist(cur_purchase.getFloristEmail());
                Map<String, Vector> inventoryMap = florist.getInventory().getInventoryMap();
                Product product = (Product) inventoryMap.get(productName).firstElement();
                double productPrice = product.getPrice();

                double cancelPrice = productPrice * cur_purchase.getQuantity();
                cart.setTotalprice(curPrice - cancelPrice);
            }
        }

        return customerRepository.removeCart(email, orderID);
    }
    public List<Purchase> viewCart(String email) throws Exception{
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
    public int editCart(String email, Purchase purchase) throws Exception{
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
            List<Purchase> cur_cart = customerRepository.updateHistory(email);
            for(Purchase purchase : cur_cart){
                floristRepsoitory.updateHistory(purchase, purchase.getFloristEmail());
            }
            //empty cart
            customerRepository.emptyCart(email);
            return true;
        }
        else throw new Exception("Email does not exist");
    }
    public List<Purchase> viewHistory(String email) throws Exception{
        if(customerRepository.isValid(email)) {
            return customerRepository.viewHistory(email);
        }
        else throw new Exception("Email does not exist");
    }
}
