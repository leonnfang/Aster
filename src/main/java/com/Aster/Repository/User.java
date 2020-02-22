package com.Aster.Repository;


public class User {
    private String user_name;
    private String password;
    private String email;
    private String address;
    private String lastName;
    private String firstName;

    User(UserBuilder ub) {
        if(ub.user_name == null || ub.password == null){
            throw new NullPointerException("The information is not complete");
        }
        this.user_name = ub.user_name;
        this.lastName = ub.lastName;
        this.firstName = ub.firstName;
        this.email = ub.email;
        this.address = ub.address;
        this.password = ub.password;
    }

    String get_Username() {
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
    static class UserBuilder {
        String user_name;
        String password;
        String email;
        String address;
        String lastName;
        String firstName;

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
        public User build(){
            return new User(this);
        }
    }
}
