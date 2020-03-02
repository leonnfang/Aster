package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer{
    private String user_name;
    private String password;
    private String email;
    private String address;
    private History history;
    private Cart cart;

    /*public Customer(@JsonProperty("user_name") String user_name,
                    @JsonProperty("password") String password,
                    @JsonProperty("email") String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }*/

    public Customer(@JsonProperty("user_name") String user_name,
                    @JsonProperty("password") String password,
                    @JsonProperty("email") String email,
                    @JsonProperty("address") String address,
                    @JsonProperty("history") History history,
                    @JsonProperty("cart") Cart cart) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.history = history;
        this.cart = cart;
    }

    public Customer(){

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
