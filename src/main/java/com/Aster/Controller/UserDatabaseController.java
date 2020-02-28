package com.Aster.Controller;
import com.Aster.Database.UserDB;
import com.Aster.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String Get_username(@RequestParam String email) throws Exception{
        System.out.println("getting username");
        return userdb.Get_username(email);
    }
    @GetMapping("/GET_user")
    public User Get_user(@RequestParam String email){
        return userdb.Get_user(email);
    }

}
