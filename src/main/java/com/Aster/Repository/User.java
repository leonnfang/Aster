package com.Aster.Repository;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String user_name;
    private String password;
    private String email;
    private String address;
    private String lastName;
    private String firstName;
    private History history;

    public User(@JsonProperty("user_name") String user_name,@JsonProperty("password") String password,@JsonProperty("email") String email) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
    }

    public User(@JsonProperty String user_name,
                @JsonProperty String password,
                @JsonProperty String email,
                @JsonProperty String address,
                @JsonProperty String lastName,
                @JsonProperty String firstName,
                @JsonProperty History history) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.lastName = lastName;
        this.firstName = firstName;
        this.history = history;
    }
    public String getEmail(){
        return email;
    }
    public String getUser_name(){return user_name;}

    public String getPassword() { return password; }

    public String getAddress() { return address; }

    public String getFirstName() { return firstName; }

    public History getHistory() { return history; }

    public String getLastName() { return lastName; }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setHistory(History history) { this.history = history; }
}

/*

 */
