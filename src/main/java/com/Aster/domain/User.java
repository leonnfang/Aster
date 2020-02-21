package com.Aster.domain;

public class User {
    private String user_name;
    private String password;
    private String email;
    private String address;
    User(){

    }
    String get_Username(){
        return user_name;
    }
    String getPassword(){
        return password;
    }
    String getEmail(){
        return email;
    }
    String getAddress(){
        return address;
    }
}
