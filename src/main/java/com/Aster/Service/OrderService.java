package com.Aster.Service;

import com.Aster.Repository.CustomerRepository;
import com.Aster.Repository.FloristRepository;
import com.Aster.Model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private FloristRepository floristRepsoitory;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderService(FloristRepository floristRepsoitory, CustomerRepository customerRepository) {
        this.floristRepsoitory = floristRepsoitory;
        this.customerRepository = customerRepository;
    }

    public boolean addOrder(Purchase purchase) throws Exception {
        if(floristRepsoitory.addOrder(purchase) != 0 || customerRepository.addOrder(purchase) != 0){
            throw new Exception("Cannot place the order");
        }
        return true;
    }
    public boolean cancelOrder(Purchase purchase) throws Exception {
        if(floristRepsoitory.cancelOrder(purchase) != 0 || customerRepository.cancelOrder(purchase) != 0){
            throw new Exception("cannot cancel the order");
        }
        return true;
    }
}
