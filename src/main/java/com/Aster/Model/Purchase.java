package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "PURCHASE_ID", nullable = false, unique = true)
    private String OrderId;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HISTORY_ID")
    private History history;

    public Purchase(@JsonProperty("floristEmail") String floristEmail,
                    @JsonProperty("customerEmail") String customerEmail,
                    @JsonProperty("date") String date,
                    @JsonProperty("product") String productName,
                    @JsonProperty("quantity") int quantity,
                    @JsonProperty("complete") boolean complete,
                    @JsonProperty("id") String OrderId){
        this.floristEmail = floristEmail;
        this.customerEmail = customerEmail;
        this.date = date;
        this.productName = productName;
        this.quantity = quantity;
        this.complete = false;
        this.OrderId = OrderId;
    }

    public Purchase(){}

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
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

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String OrderId) {
        this.OrderId = OrderId;
    }

}
