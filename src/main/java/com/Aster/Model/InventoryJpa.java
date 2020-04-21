package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "INVENTORYJPA")
public class InventoryJpa {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVENTORYJPA_ID")
    private Long Id;

    @Column(nullable = false)
    private boolean isEmpty = true;
    @Column(nullable = false)
    private int totalNumber = 0;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FLORIST_ID")
    @JsonIgnore
    private FloristJpa floristJpa;

    @OneToMany(mappedBy = "inventoryJpa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductJpa> inventoryJpa = new ArrayList<>();

    public InventoryJpa(@JsonProperty("inventoryMap") List<ProductJpa> inventoryJpa,
                     @JsonProperty("isEmpty") boolean isEmpty,
                     @JsonProperty("totalNumber") int totalNumber) {
        this.inventoryJpa = inventoryJpa;
        this.isEmpty = isEmpty;
        this.totalNumber = totalNumber;
    }

    public InventoryJpa(){}

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

    public FloristJpa getFloristJpa() {
        return floristJpa;
    }

    public void setFloristJpa(FloristJpa floristJpa) {
        this.floristJpa = floristJpa;
    }

    public List<ProductJpa> getInventoryJpa() {
        return inventoryJpa;
    }

    public void setInventoryJpa(List<ProductJpa> inventoryJpa) {
        this.inventoryJpa = inventoryJpa;
    }
}
