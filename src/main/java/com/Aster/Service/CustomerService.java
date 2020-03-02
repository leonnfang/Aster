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
        customerDB.addCustomer(customer);
        return 0;
    }
    public String getCustomername(String email) throws Exception{
        return customerDB.getCustomername(email);
    }
    public int deleteCustomer(String email) throws Exception{
        return customerDB.deleteCustomer(email);
    }


    public int addCart(String email, Order order) throws Exception{
        return customerDB.addCart(email, order);
    }
    public List<Order> viewCart(String email) throws Exception{
        return customerDB.viewCart(email);
    }
    public int removeCart(String email, Order order) throws Exception{
        return customerDB.removeCart(email, order);
    }
    public int emptyCart(String email) throws Exception{
        return customerDB.emptyCart(email);
    }


    public int checkout(String email) throws Exception{
        //last check of cart
        customerDB.viewCart(email);
        //make payment

        //send order to florist

        //update history

        //empty cart
        customerDB.emptyCart(email);
        return 0;
    }
}
