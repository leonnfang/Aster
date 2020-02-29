package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
public class Florist{
    private Inventory inventory;
    private String user_name;
    private String password;
    private String email;
    private String address;
    private String lastName;
    private String firstName;
    private History history;
    public Florist(Inventory inventory, String user_name, String password, String email, String address, String lastName, String firstName, History history) {
        this.inventory = inventory;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
        this.history = history;
    }
    /*
    public Florist(String user_name, String password, String email) {
        super(user_name, password, email);
    }
    *./
     */
    /*Florist(UserBuilder ub) {
        super(ub);
    }*/

}
