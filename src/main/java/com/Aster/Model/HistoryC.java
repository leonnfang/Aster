package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "HISTORYC")
public class HistoryC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORYC_ID")
    private Long Id;

    @Column
    private boolean allCompleted = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "historyC", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> historyC = new ArrayList<>();


    public HistoryC(@JsonProperty("historyC") List<Purchase> historyC,
                    @JsonProperty("allcompleted") boolean allCompleted) {
        this.allCompleted = allCompleted;
        this.historyC = historyC;
    }

    public HistoryC(){}

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

    public List<Purchase> getHistoryC() {
        return historyC;
    }

    public void setHistoryC(List<Purchase> historyC) {
        this.historyC = historyC;
    }
}