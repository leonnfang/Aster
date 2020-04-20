package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cart")
public class Profile {
    @Id
    private Long id;
    @Column
    private double totalprice;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    private Customer customer;

    @OneToMany(mappedBy = "profile_cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> cart;
    @OneToMany(mappedBy = "profile_history", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> history;

    public Profile(@JsonProperty("cart") List<Order> cart,
                   @JsonProperty("history") List<Order> history,
                   @JsonProperty("totalprice") double totalprice){
        this.cart = cart;
        this.history = history;
        this.totalprice = totalprice;
    }

    public Profile(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Order> getCart() {
        return cart;
    }

    public void setCart(List<Order> cart) {
        this.cart = cart;
    }

    public List<Order> getHistory() {
        return history;
    }

    public void setHistory(List<Order> history) {
        this.history = history;
    }
}
