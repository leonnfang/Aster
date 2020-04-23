package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HISTORYF")
public class HistoryF {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORYF_ID")
    private Long Id;

    @Column
    private boolean allCompleted = true;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLORIST_ID")
    @JsonIgnore
    private Florist florist;

    @OneToMany(mappedBy = "historyF", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Purchase> historyF = new ArrayList<>();


    public HistoryF(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public boolean isAllCompleted() {
        return allCompleted;
    }

    public void setAllCompleted(boolean allCompleted) {
        this.allCompleted = allCompleted;
    }

    public Florist getFlorist() {
        return florist;
    }

    public void setFlorist(Florist florist) {
        this.florist = florist;
    }

    public List<Purchase> getHistoryF() {
        return historyF;
    }

    public void setHistoryF(List<Purchase> historyF) {
        this.historyF = historyF;
    }
}
