package com.Aster.Model;

public class Florist extends User{
    private Inventory inventory;

    public Florist(String user_name, String password, String email) {
        super(user_name, password, email);
    }

    public Inventory getInventory() {
        return inventory;
    }
    /*Florist(UserBuilder ub) {
        super(ub);
    }*/
}
