package com.Aster.Model;
import com.Aster.Model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;
import java.util.*;
public class Inventory {
    private Map<String,Integer> inventoryMap;
    private boolean isEmpty;
    private int totalNumber;
    public Inventory(@JsonProperty("inventoryMap") Map<String,Integer> inventoryMap,
                     @JsonProperty("isEmpty") boolean isEmpty,
                     @JsonProperty("totalNumber") int totalNumber) {
        this.inventoryMap = inventoryMap;
        this.isEmpty = isEmpty;
        this.totalNumber = totalNumber;
    }

    public Map<String, Integer> getInventoryMap() {
        return inventoryMap;
    }

    public void setInventoryMap(Map<String, Integer> inventoryMap) {
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
        totalNumber = 0;
        for(Map.Entry<String,Integer> e : inventoryMap.entrySet()){
            totalNumber += e.getValue();
        }
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }
}
