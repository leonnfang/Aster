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

    public User(String user_name, String password, String email, String address, String lastName, String firstName, History history) {
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
    /*User(UserBuilder ub) {
        if(ub.user_name == null || ub.password == null){
            throw new NullPointerException("The information is not complete");
        }
        this.user_name = ub.user_name;
        this.lastName = ub.lastName;
        this.firstName = ub.firstName;
        this.email = ub.email;
        this.address = ub.address;
        this.password = ub.password;
        this.history = ub.history;
    }
*/
    /*String get_Username() {
        return user_name;
    }
    String getPassword() {
        return password;
    }
    String getEmail() {
        return email;
    }
    String getAddress() {
        return address;
    }
    String getLastName(){
        return lastName;
    }
    String getFirstName(){
        return firstName;
    }
    History getHistory(){return history; }
    static class UserBuilder {
        String user_name;
        String password;
        String email;
        String address;
        String lastName;
        String firstName;
        History history;
        public UserBuilder withid(String id) {
            this.user_name = id;
            return this;
        }
        public UserBuilder withPassword(String password){
            this.password = password;
            return this;
        }
        public UserBuilder withEmail(String email){
            this.email = email;
            return this;
        }
        public UserBuilder withaddress(String address){
            this.address = address;
            return this;
        }
        public UserBuilder withlastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder withfirstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public UserBuilder withHistory(History history){
            this.history = history;
            return this;
        }
        public User build(){
            return new User(this);
        }
    }*/
}

/*

 */
