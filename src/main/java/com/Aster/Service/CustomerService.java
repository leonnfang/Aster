package com.Aster.Service;

import com.Aster.Database.CustomerDB;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private CustomerDB customerDB;

    @Autowired
    public CustomerService(CustomerDB customerDB){this.customerDB = customerDB;}

    public int addCustomer(Customer customer) throws Exception{
        customerDB.addUser(customer);
        return 0;
    }

    public String getCustomername(String email) throws Exception{
        return customerDB.getUsername(email);
    }

    public int deleteCustomer(String email) throws Exception{
        return customerDB.deleteUser(email);
    }

    public int addtoCart(String email, Order order) throws Exception{

        return customerDB.addtoCart(email, order);
    }

}
