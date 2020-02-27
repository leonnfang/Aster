package com.Aster.Controller;
import com.Aster.Database.UserDB;
import com.Aster.Repository.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import java.util.List;

@RestController
public class UserDatabaseController {
    private UserDB userdb;
    // Local Variable of UserDatabaseController
    @Autowired
    public UserDatabaseController(UserDB userdb){this.userdb = userdb;}
    // Constructor of UserDatabaseController

    @PostMapping("/ADD_USER")
    public int Add_User(@RequestBody User user) throws Exception {
        return userdb.AddUser(user);
    }
    @GetMapping("/GET_username")
    public String Get_username(@RequestBody String email) throws Exception{
        System.out.println("getting username");
        return userdb.Get_username(email);
    }

}
