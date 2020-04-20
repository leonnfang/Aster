package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(nullable = false, unique = true)
    private String id;
    @Column(nullable = false)
    private String floristEmail;
    @Column(nullable = false)
    private String customerEmail;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private boolean complete = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private Profile profile_cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn
    private Profile profile_history;

    public Order(@JsonProperty("floristEmail") String floristEmail,
                 @JsonProperty("customerEmail") String customerEmail,
                 @JsonProperty("date") String date,
                 @JsonProperty("product") String productName,
                 @JsonProperty("quantity") int quantity,
                 @JsonProperty("complete") boolean complete,
                 @JsonProperty("id") String id){
        this.floristEmail = floristEmail;
        this.customerEmail = customerEmail;
        this.date = date;
        this.productName = productName;
        this.quantity = quantity;
        this.complete = false;
        this.id = id;
    }

    public Profile getProfile_history() {
        return profile_history;
    }

    public void setProfile_history(Profile profile_history) {
        this.profile_history = profile_history;
    }

    public Profile getProfile_cart() {
        return profile_cart;
    }

    public void setProfile_cart(Profile profile_cart) {
        this.profile_cart = profile_cart;
    }

    public String getFloristEmail() {
        return floristEmail;
    }

    public void setFloristEmail(String floristEmail) {
        this.floristEmail = floristEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
