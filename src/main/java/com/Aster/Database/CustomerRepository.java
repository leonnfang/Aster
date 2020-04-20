package com.Aster.Database;

import com.Aster.Model.Profile;
import com.Aster.Model.Customer;
import com.Aster.Model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(Customer customer) throws Exception;

    Customer getCustomer(String email) throws Exception;

    boolean deleteCustomer(String email) throws Exception;

    boolean isValid(String email);

    boolean addCart(String email, Order order) throws Exception;

    boolean removeCart(String email, String orderID) throws Exception;

    boolean emptyCart(String email) throws Exception;

    List<Order> viewCart(String email);

    boolean isInCart(String email, Order order) throws Exception;

    boolean updateCart(String email, Order order) throws Exception;

    int getQuantity(String email, Order order) throws Exception;

    List<Order> viewHistory(String email);

    List<Order> updateHistory(String email);

    int addOrder(Order order);

    int cancelOrder(Order order);
}
