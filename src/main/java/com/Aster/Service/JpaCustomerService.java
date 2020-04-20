package com.Aster.Service;

import com.Aster.Database.JpaCustomerRepository;
import com.Aster.Model.Cart;
import com.Aster.Model.Customer;
import com.Aster.Model.History;
import com.Aster.Model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCustomerService {
    @Autowired
    private JpaCustomerRepository jpaCustomerRepository;

    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(jpaCustomerRepository.containsEmail(customer.getEmail())){
            throw new ExportException("Customer Already Exists");
        }

        List<Purchase> newHistory = new ArrayList<>();
        List<Purchase> newCart = new ArrayList<>();
        Cart cart = new Cart(newCart, 0);
        History history = new History(newHistory);

        Customer newCustomer = new Customer(customer.getUser_name(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getAddress(),
                cart,
                history);

        jpaCustomerRepository.save(customer);
        return true;
    }
    public Customer getCustomer(String email) throws Exception{
        if(email == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaCustomerRepository.containsEmail(email)){
            throw new Exception("Customer Does Not Exist");
        }
        return jpaCustomerRepository.findByEmail(email);
    }
    public boolean deleteCustomer(String email) throws Exception{
        if(!jpaCustomerRepository.containsEmail(email)){
            throw new Exception("Customer Does Not Exist");
        }
        jpaCustomerRepository.delete(jpaCustomerRepository.findByEmail(email));
        return true;
    }
    public List<Customer> viewCustomers(){
        return jpaCustomerRepository.findAll();
    }


}
