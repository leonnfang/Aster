package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "HISTORY")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private Long Id;

    @Column
    private boolean allCompleted = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLORISTJPA_ID")
    @JsonIgnore
    private FloristJpa floristJpa;

    @OneToMany(mappedBy = "history", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> history = new ArrayList<>();


    public History(@JsonProperty("history") List<Purchase> history,
                   @JsonProperty("allcompleted") boolean allCompleted) {
        this.allCompleted = allCompleted;
        this.history = history;
    }

    public History(){}

    public FloristJpa getFloristJpa() {
        return floristJpa;
    }

    public void setFloristJpa(FloristJpa floristJpa) {
        this.floristJpa = floristJpa;
    }

    public boolean isAllCompleted() {
        return allCompleted;
    }

    public void setAllCompleted(boolean allCompleted) {
        this.allCompleted = allCompleted;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Purchase> getHistory() {
        return history;
    }

    public void setHistory(List<Purchase> history) {
        this.history = history;
    }
}