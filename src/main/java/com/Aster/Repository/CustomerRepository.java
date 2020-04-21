package com.Aster.Repository;

import com.Aster.Model.Customer;
import com.Aster.Model.Purchase;

import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(Customer customer) throws Exception;

    Customer getCustomer(String email) throws Exception;

    boolean deleteCustomer(String email) throws Exception;

    boolean isValid(String email);

    boolean addCart(String email, Purchase purchase) throws Exception;

    boolean removeCart(String email, String orderID) throws Exception;

    boolean emptyCart(String email) throws Exception;

    List<Purchase> viewCart(String email);

    boolean isInCart(String email, Purchase purchase) throws Exception;

    boolean updateCart(String email, Purchase purchase) throws Exception;

    int getQuantity(String email, Purchase purchase) throws Exception;

    List<Purchase> viewHistory(String email);

    List<Purchase> updateHistory(String email);

    int addOrder(Purchase purchase);

    int cancelOrder(Purchase purchase);
}
