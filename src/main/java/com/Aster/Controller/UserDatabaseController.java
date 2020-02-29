package com.Aster.Controller;
import com.Aster.Database.UserDB;
import com.Aster.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
public class UserDatabaseController {
    private UserDB userdb;
    // Local Variable of UserDatabaseController
    @Autowired
    public UserDatabaseController(UserDB userdb){this.userdb = userdb;}
    // Constructor of UserDatabaseController

    @PostMapping("/ADD_USER")
    public int addUser(@RequestBody User user) throws Exception {
        return userdb.addUser(user);
    }

    @GetMapping("/GET_USERNAME")
    public String getUsername(@RequestParam String email) throws Exception{
        System.out.println("getting username");
        return userdb.getUsername(email);
    }

    @GetMapping("/DELETE_USER")
    public int deleteUser(@RequestParam String email) throws Exception{
        System.out.println("deleting user with email: " + email);
        return userdb.deleteUser(email);
    }


}
