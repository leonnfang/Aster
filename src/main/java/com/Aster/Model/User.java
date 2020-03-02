package com.Aster.Model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    public User(@JsonProperty("user_name") String user_name,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email,
                @JsonProperty("address") String address,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("history") History history) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
        this.history = history;
    }

    public String getEmail() {
        return "";
    }

    public String getUsername(){
        return "";
    }
}

/*

 */
