package com.Aster.Service;

import com.Aster.Database.JpaCartRepository;
import com.Aster.Database.JpaCustomerRepository;
import com.Aster.Database.JpaHistoryRepository;
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
    @Autowired
    private JpaCartRepository jpaCartRepository;
    @Autowired
    private JpaHistoryRepository jpaHistoryRepository;

    public boolean addCustomer(Customer customer) throws Exception{
        if(customer == null){
            throw new Exception("Invalid Customer");
        }
        if(jpaCustomerRepository.customerExists(customer.getEmail())){
            throw new ExportException("Customer Already Exists");
        }

        Cart cart = new Cart();
        History history = new History();

        /*Customer newCustomer = new Customer(customer.getUser_name(),
                customer.getPassword(),
                customer.getEmail(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getAddress());*/


        cart.setCustomer(customer);
        customer.setCart(cart);
        history.setCustomer(customer);
        customer.setHistory(history);

        //jpaCartRepository.save(cart);
        //jpaHistoryRepository.save(history);
        jpaCustomerRepository.save(customer);
        return true;
    }
    public Customer getCustomer(String email) throws Exception{
        if(email == null){
            throw new Exception("Invalid Email");
        }
        if(!jpaCustomerRepository.customerExists(email)){
            throw new Exception("Customer Does Not Exist");
        }
        return jpaCustomerRepository.findByEmail(email);
    }
    public boolean deleteCustomer(String email) throws Exception{
        if(!jpaCustomerRepository.customerExists(email)){
            throw new Exception("Customer Does Not Exist");
        }
        jpaCustomerRepository.delete(jpaCustomerRepository.findByEmail(email));
        return true;
    }
    public List<String> viewCustomers(){
        return jpaCustomerRepository.findAllEmail();
    }

    public boolean addCart(String email, Purchase purchase) throws Exception{
        if(!jpaCustomerRepository.customerExists(purchase.getCustomerEmail())){
            throw new Exception("Customer Does Not Exist");
        }
        // TODO check if florist exist

        Customer customer = jpaCustomerRepository.findByEmail(email);

        return true;
    }

}
