package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Florist {

    private String user_name;
    private String password;
    private String email;
    private String lastName;
    private String firstName;
    private String address;
    private History history;
    private Inventory inventory;

    public Florist(@JsonProperty("user_name") String user_name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("address") String address,
                   @JsonProperty("inventory") Inventory inventory,
                   @JsonProperty("history") History history) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.inventory = inventory;
        this.history = history;
    }

    /*
    public Florist(@JsonProperty String user_name,
                   @JsonProperty String password,
                   @JsonProperty String email) {
    }
     */

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
