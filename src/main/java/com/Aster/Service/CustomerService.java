package com.Aster.Service;

import com.Aster.Database.CustomerDB;
import com.Aster.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*public int addCart(String email, Order order) throws Exception{
        return customerDB.addCart(email, order);
    }*/

}
