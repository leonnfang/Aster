package com.Aster.Service;

import com.Aster.Database.CustomerDB;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerDB customerDB;

    @Autowired
    public CustomerService(CustomerDB customerDB){this.customerDB = customerDB;}

    public int addCustomer(Customer customer) throws Exception{
        return customerDB.addCustomer(customer);
    }
    public Customer getCustomer(String email) throws Exception{
        return customerDB.getCustomer(email);
    }
    public int deleteCustomer(String email) throws Exception{
        return customerDB.deleteCustomer(email);
    }


    public int addCart(String email, Order order) throws Exception{
        //TODO Have to add Checking whether stock exists
        if(customerDB.isvalid(email)) {
            return customerDB.addCart(email, order);
        }
        else throw new Exception("Email does not exist");
    }
    public List<Order> viewCart(String email) throws Exception{
        if(customerDB.isvalid(email)) {
            return customerDB.viewCart(email);
        }
        else throw new Exception("Email does not exist");
    }
    public int removeCart(String email, String orderID) throws Exception{
        if(customerDB.isvalid(email)) {
            return customerDB.removeCart(email, orderID);
        }
        else throw new Exception("Email does not exist");
    }
    public int emptyCart(String email) throws Exception{
        if(customerDB.isvalid(email)) {
            return customerDB.emptyCart(email);
        }
        else throw new Exception("Email does not exist");
    }


    public int checkout(String email) throws Exception{
        if(customerDB.isvalid(email)) {
            //last check of cart
            customerDB.viewCart(email);
            //make payment

            //send order to florist

            //update history both
            customerDB.updateHistory(email);
            //empty cart
            customerDB.emptyCart(email);
            return 0;
        }
        else throw new Exception("Email does not exist");
    }
    public List<Order> viewHistory(String email) throws Exception{
        if(customerDB.isvalid(email)) {
            return customerDB.viewHistory(email);
        }
        else throw new Exception("Email does not exist");
    }
}
