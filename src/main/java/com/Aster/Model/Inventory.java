package com.Aster.Model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;


public class Inventory {

    private Map<String,Vector> inventoryMap;
    private boolean isEmpty;
    private int totalNumber;

    public Inventory(@JsonProperty("inventoryMap") Map<String,Vector> inventoryMap,
                     @JsonProperty("isEmpty") boolean isEmpty,
                     @JsonProperty("totalNumber") int totalNumber) {
        this.inventoryMap = inventoryMap;
        this.isEmpty = isEmpty;
        this.totalNumber = totalNumber;
    }

    public Map<String, Vector> getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(Map<String, Vector> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }

    public boolean isEmpty() {
        if(getTotalNumber() == 0){
            isEmpty = true;
        }else{
            isEmpty = false;
        }
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
}
