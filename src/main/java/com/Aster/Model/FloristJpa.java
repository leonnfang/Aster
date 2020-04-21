package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "FLORISTJPA")
public class FloristJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLORISTJPA_ID")
    private Long Id;

    @Column(nullable = false, unique = true)
    private String user_name;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "INVENTORYJPA_ID")
    private InventoryJpa inventoryJpa;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "HISTORY_ID")
    private History history;


    public FloristJpa(@JsonProperty("user_name") String user_name,
                   @JsonProperty("password") String password,
                   @JsonProperty("email") String email,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("address") String address
                   ) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
    }

    public FloristJpa(){

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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public InventoryJpa getInventoryJpa() {
        return inventoryJpa;
    }

    public void setInventoryJpa(InventoryJpa inventoryJpa) {
        this.inventoryJpa = inventoryJpa;
    }
}
