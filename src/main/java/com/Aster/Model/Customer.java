package com.Aster.Model;

import org.springframework.stereotype.Component;

public class Customer extends User {
    public Customer(String user_name, String password, String email) {
        super(user_name, password, email);
    }
    /*Customer(UserBuilder ub) {
        super(ub);
    }*/
}
