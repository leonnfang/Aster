package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;

public class Florist {
    private Inventory inventory;
    private String user_name;
    private String password;
    private String email;
    private String address;
    private String lastName;
    private String firstName;
    private History history;

    public Florist(@JsonProperty("user_name") String user_name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public Florist(@JsonProperty Inventory inventory,
                   @JsonProperty String user_name,
                   @JsonProperty String password,
                   @JsonProperty String email,
                   @JsonProperty String address,
                   @JsonProperty String lastName,
                   @JsonProperty String firstName,
                   @JsonProperty History history) {
        this.inventory = inventory;
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
        this.history = history;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

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

    public Inventory getInventory() {
        return inventory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
