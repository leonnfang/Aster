package com.Aster.Database;

import com.Aster.Model.Cart;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Transactional
@Repository
public class JdbiCustomerRepository implements CustomerRepository {

    @Override
    public boolean addCustomer(Customer customer) throws Exception {
        return false;
    }

    @Override
    public Customer getCustomer(String email) throws Exception {
        return null;
    }

    @Override
    public boolean deleteCustomer(String email) throws Exception {
        return false;
    }

    @Override
    public boolean isValid(String email) {
        return false;
    }

    @Override
    public boolean addCart(String email, Order order) throws Exception {
        return false;
    }

    @Override
    public boolean removeCart(String email, String orderID) throws Exception {
        return false;
    }

    @Override
    public boolean emptyCart(String email) throws Exception {
        return false;
    }

    @Override
    public Cart viewCart(String email) {
        return null;
    }

    @Override
    public boolean isInCart(String email, Order order) throws Exception {
        return false;
    }

    @Override
    public boolean updateCart(String email, Order order) throws Exception {
        return false;
    }

    @Override
    public int getQuantity(String email, Order order) throws Exception {
        return 0;
    }

    @Override
    public List<Order> viewHistory(String email) {
        return null;
    }

    @Override
    public List<Order> updateHistory(String email) {
        return null;
    }

    @Override
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public int cancelOrder(Order order) {
        return 0;
    }
}
