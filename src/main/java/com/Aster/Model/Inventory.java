package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVENTORY_ID")
    private Long Id;

    @Column(nullable = false)
    private boolean isEmpty = true;
    @Column(nullable = false)
    private int totalNumber = 0;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FLORIST_ID")
    @JsonIgnore
    private Florist florist;

    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> inventory = new ArrayList<>();

    public Inventory(@JsonProperty("inventoryMap") List<Product> inventory,
                     @JsonProperty("isEmpty") boolean isEmpty,
                     @JsonProperty("totalNumber") int totalNumber) {
        this.inventory = inventory;
        this.isEmpty = isEmpty;
        this.totalNumber = totalNumber;
    }

    public Inventory(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Florist getFlorist() {
        return florist;
    }

    public void setFlorist(Florist florist) {
        this.florist = florist;
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }
}
