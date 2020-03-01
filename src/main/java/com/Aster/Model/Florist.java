package com.Aster.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Florist extends User{
    private Inventory inventory;
    public Florist(String user_name, String password, String email) {
        super(user_name, password, email);
    }
    public Florist(String user_name,
                String password,
                String email,
                String address,
                String lastName,
                String firstName,
                History history,
                @JsonProperty Inventory inventory) {
        super(user_name,password,email,address,lastName,firstName,history);
        this.inventory = inventory;
    }
    public Inventory getInventory() {
        return inventory;
    }

}
