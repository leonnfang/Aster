package com.Aster.Repository;

public class Florist extends User{
    private Inventory inventory;

    public Florist(String user_name, String password, String email) {
        super(user_name, password, email);
    }
    /*Florist(UserBuilder ub) {
        super(ub);
    }*/
}
