package com.Aster.Model;

public class Customer extends User {
    private Cart cart;

    public Customer(String user_name, String password, String email) {
        super(user_name, password, email);
    }

    public Cart getCart(){
        return cart;
    }
}
