package com.Aster.Service;

import com.Aster.Database.CustomerRepository;
import com.Aster.Database.FloristRepository;
import com.Aster.Model.Order;
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

    public boolean addOrder(Order order) throws Exception {
        if(floristRepsoitory.addOrder(order) != 0 || customerRepository.addOrder(order) != 0){
            throw new Exception("Cannot place the order");
        }
        return true;
    }
    public boolean cancelOrder(Order order) throws Exception {
        if(floristRepsoitory.cancelOrder(order) != 0 || customerRepository.cancelOrder(order) != 0){
            throw new Exception("cannot cancel the order");
        }
        return true;
    }
}
